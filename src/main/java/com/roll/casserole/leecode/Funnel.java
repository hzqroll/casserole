package com.roll.casserole.leecode;

/**
 * <p>@author roll
 * <p>created on 2020/10/12 9:51 上午
 */
public class Funnel {
    // 容量
    private final int capacity;
    // 每毫秒漏水的速度
    private final double leakingRate;
    // 漏斗没有被占满的体积
    private int emptyCapacity;
    // 上次漏水的时间
    private long lastLeakingTime = System.currentTimeMillis();

    public Funnel(int capacity, double leakingRate) {
        this.capacity = capacity;
        this.leakingRate = leakingRate;
        this.emptyCapacity = capacity;
    }

    // 腾出空间
    public void makeSpace() {
        long currentTime = System.currentTimeMillis();
        // 计算上次漏出多少数据
        long time = currentTime - lastLeakingTime;
        // 计算漏出的数据
        int count = (int) (time * leakingRate);
        // 给剩余的加上腾出的空间
        emptyCapacity = emptyCapacity + count;
        if (emptyCapacity > capacity) {
            emptyCapacity = capacity;
        }
        lastLeakingTime = System.currentTimeMillis();
    }

    public boolean isAllowed(int count) {
        makeSpace();
        // 判断是否够用，如果够了，直接减掉
        if (emptyCapacity > count) {
            emptyCapacity = emptyCapacity - count;
            return true;
        }
        // 不够了
        return false;
    }

    public static void main(String[] args) {
        Funnel funnel = new Funnel(100, 10);
        for (int i = 0; i < 10000; i++) {
            if (funnel.isAllowed(i * 2)) {
                System.out.println(true);
            } else {
                System.out.println(false);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
