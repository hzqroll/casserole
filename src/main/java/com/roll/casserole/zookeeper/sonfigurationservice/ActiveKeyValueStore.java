package com.roll.casserole.zookeeper.sonfigurationservice;

import com.roll.casserole.zookeeper.ConnectionWatcher;
import com.roll.casserole.zookeeper.CreateGroup;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;

import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * @author zongqiang.hao
 * created on 2018/10/2 下午9:23.
 */
public class ActiveKeyValueStore extends ConnectionWatcher {

    private static final Charset CHARSET = Charset.forName("utf-8");

    /*public void write(String path, String value) {
        Stat stat;
        try {
            stat = zooKeeper.exists(path, false);
            if (stat == null) {
                zooKeeper.create(path, value.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            } else {
                zooKeeper.setData(path, value.getBytes(CHARSET), -1);
            }
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }*/

    public void write(String path, String value) throws KeeperException, InterruptedException {
        int retires = 0;
        while (true) {
            try {
                Stat stat = zooKeeper.exists(path, false);
                if (stat == null) {
                    zooKeeper.create(path, value.getBytes(CHARSET), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                } else {
                    zooKeeper.setData(path, value.getBytes(CHARSET), stat.getVersion());
                }
                return;
            } catch (KeeperException.SessionExpiredException e) {
                throw e;
            } catch (KeeperException e) {
                if (retires++ == 10) {
                    throw e;
                }
                TimeUnit.SECONDS.sleep(10);
            }
        }
    }

    public String read(String path, Watcher watcher) {
        byte[] data = new byte[0];
        try {
            data = zooKeeper.getData(path, watcher, null);
            return new String(data, CHARSET);
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
        return "no data";
    }
}
