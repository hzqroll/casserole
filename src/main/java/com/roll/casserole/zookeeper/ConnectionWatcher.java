package com.roll.casserole.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author zongqiang.hao
 * created on 2018/10/2 下午9:28.
 */
public class ConnectionWatcher implements Watcher {
    private static final int TIME_OUT = 2000;

    public ZooKeeper zooKeeper;

    //锁存器(latch)此计数器为1，表示在释放所有等待线程之前需要发生的事件数，
    private CountDownLatch connectedSignal = new CountDownLatch(1);

    public void connect(String hosts) {
        //参数this表示一个Watcher对象接收来自于Zookeeper的回调，以获得各种事件的通知，在此表示CreateGroup对象
        try {
            zooKeeper = new ZooKeeper(hosts, TIME_OUT, this);
            connectedSignal.await();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void process(WatchedEvent event) {
        if (event.getState() == Event.KeeperState.SyncConnected) {
            connectedSignal.countDown();
        }
    }

    public void close() throws InterruptedException {
        zooKeeper.close();
    }
}
