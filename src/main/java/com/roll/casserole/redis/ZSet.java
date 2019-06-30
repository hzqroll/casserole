package com.roll.casserole.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

/**
 * @author zongqiang.hao
 * created on 2019-05-12 15:33.
 */
public class ZSet {

    private Jedis jedis;

    private ZSet(Jedis jedis) {
        this.jedis = jedis;
    }

    /**
     * 每当有一个行为到来时,都维护一次时间窗口,将时间窗口以外的记录都清理掉,只保留窗口内的记录
     * zset 集合中之后 score 值非常重要.
     * 因为这几个连续的 redis 操作都是针对同一个 key 的,使用 pipeline 可以显著提升 redis
     * 存取效率. 但是这种方案也有缺点,因为他要记录时间窗口内所有的行为记录,如果这个量很大,比如限定
     * 60s 内操作不得超过 100w 这样的参数,这样会消耗大量存储空间.
     *
     * @param userId
     * @param actionKey
     * @param period    过期时间
     * @param maxCount  最大数
     * @return 是否超过
     */
    private boolean isActionAllowed(String userId, String actionKey, int period, int maxCount) {
        String key = String.format("hits:%S:%s", userId, actionKey);
        long nowTs = System.currentTimeMillis();
        Pipeline pipeline = jedis.pipelined();
        pipeline.multi();
        pipeline.zadd(key, nowTs, "" + nowTs);
        // 删除过期数据
        pipeline.zremrangeByScore(key, 0, nowTs - period * 1000);
        Response<Long> count = pipeline.zcard(key);
        pipeline.expire(key, period + 1);
        pipeline.exec();
        pipeline.close();
        return count.get() <= maxCount;
    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");
        ZSet zSet = new ZSet(jedis);
        for (int i = 0; i < 20; i++) {
            zSet.isActionAllowed("a", "reply", 60, 5);
        }
    }
}
