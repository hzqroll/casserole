package com.roll.casserole.sync;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author roll
 * created on 2020/3/3 6:37 下午
 */
public class LockDemo extends AbstractQueuedSynchronizer {

    public LockDemo() {
        setState(1);
    }

    void acquireLock() {
        acquireShared(1);
    }

    boolean releaseLock() {
        return releaseShared(1);
    }

    /**
     * 获取资源，state-1，并且是线程共享的
     * 首先需要判断线程是否是当前线程，如果不是，
     */
    @Override
    protected int tryAcquireShared(int arg) {
        while (true) {
            if (hasQueuedPredecessors()) {
                return -1;
            }
            int state = getState();
            int remaining = state - arg;
            if (remaining >= 0) {
                // 如果设置成功，则直接返回
                if (compareAndSetState(state, remaining)) {
                    // 返回剩余数量
                    return remaining;
                }
            } else {
                // 不够了，直接返回-1
                return -1;
            }
        }
    }

    @Override
    protected boolean tryReleaseShared(int arg) {
        for (; ; ) {
            int state = getState();
            int remaining = state + arg;
            if (compareAndSetState(state, remaining)) {
                return true;
            }
        }
    }

    public static void main(String[] args) {
        LockDemo lockDemo = new LockDemo();
        new Thread(()->{
            lockDemo.acquireLock();
            System.out.println("thread 1 start");
            lockDemo.releaseLock();
        }).start();

        new Thread(()->{
            lockDemo.acquireLock();
            System.out.println("thread 2 start");
        }).start();
    }
}
