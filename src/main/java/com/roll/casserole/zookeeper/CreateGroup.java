package com.roll.casserole.zookeeper;


import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author zongqiang.hao
 * created on 2018/10/2 下午1:25.
 */
public class CreateGroup implements Watcher {

    private static final int TIME_OUT = 2000;

    private ZooKeeper zooKeeper;

    //锁存器(latch)此计数器为1，表示在释放所有等待线程之前需要发生的事件数，
    private CountDownLatch connectedSignal = new CountDownLatch(1);

    public void connect(String hosts) throws IOException, InterruptedException {
        //参数this表示一个Watcher对象接收来自于Zookeeper的回调，以获得各种事件的通知，在此表示CreateGroup对象
        zooKeeper = new ZooKeeper(hosts, TIME_OUT, this);
        connectedSignal.await();
    }

    @Override
    public void process(WatchedEvent event) {
        if (event.getState() == Event.KeeperState.SyncConnected) {
            //在调用这个方法表示计数器递减1，若计数器的值变为0，则await()方法返回
            connectedSignal.countDown();
        }
    }

    public void create(String groupName) throws KeeperException, InterruptedException {
        String path = "/" + groupName;
        String createPath = zooKeeper.create(path, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("created " + createPath);
    }

    public void join(String groupName, String memberName) throws KeeperException, InterruptedException {
        String path = "/" + groupName + "/" + memberName;
        String createdPath = zooKeeper.create(path, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("created " + createdPath);
    }

    public static void main(String args[]) throws IOException, InterruptedException, KeeperException {
        CreateGroup createGroup = new CreateGroup();
        createGroup.connect("localhost");
        //createGroup.create("zoo");
        createGroup.join("zoo", "znode2");
    }
}
