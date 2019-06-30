package com.roll.casserole.redis;

import java.util.HashMap;
import java.util.Map;

/**
 * 漏斗限流
 *
 * @author zongqiang.hao
 * created on 2019-05-12 16:43.
 */
public class Funnel {
    /**
     * 漏斗容量
     */
    int capacity;
    /**
     * 漏斗流水速率
     */
    float leakingRate;
    /**
     * 漏斗剩余空间
     */
    int leftQuota;
    /**
     * 上一次漏水时间
     */
    long leakingTs;

    public Funnel(int capacity, float leakingRate, int leftQuota, long leakingTs) {
        this.capacity = capacity;
        this.leakingRate = leakingRate;
        this.leftQuota = leftQuota;
        this.leakingTs = leakingTs;
    }

    public Funnel(int capacity, float leakingRate) {
        this.capacity = capacity;
        this.leakingRate = leakingRate;
    }

    /**
     * 获取剩余空间
     * 每次灌水钱都会被调用用以触发漏水,给漏斗腾出空间.
     * 腾出多少空间取决于过去了多久以及流水的速率
     * Funnel 对象占据的空间大小不再和行为的频率成正比,他的占用空间是一个常量.
     */
    void makeSpace() {
        long nowTs = System.currentTimeMillis();
        // 距离上一次漏水过去了多久
        long deltaTs = nowTs - leakingTs;
        // 这次还剩下去多少
        int deltaQuota = (int) (deltaTs * leakingRate);
        // 腾出的空间太少,就等下次.
        if (deltaQuota < 1) {
            return;
        }
        // 增加空余空间
        leftQuota += deltaQuota;
        // 记录漏水时间
        leakingTs = nowTs;
        // 剩余空间不得高于容量
        if (leftQuota > capacity) {
            leftQuota = capacity;
        }
    }

    boolean watering(int quota) {
        makeSpace();
        if (leftQuota >= quota) {
            leftQuota -= quota;
            return true;
        }
        return false;
    }

    private Map<String, Funnel> funnels = new HashMap<>();

    public boolean isActionAllowed(String userId, String actionKey, int capaticy, float leakingRate) {
        String key = String.format("%s:%s", userId, actionKey);
        Funnel funnel = funnels.get(key);
        if (funnel != null) {
            funnel = new Funnel(capacity, leakingRate);
            funnels.put(key, funnel);
        }
        // 需要一个 quota
        return funnel.watering(1);
    }
}
