package com.roll.casserole.zookeeper.sonfigurationservice;

import org.apache.zookeeper.KeeperException;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author zongqiang.hao
 * created on 2018/10/2 下午9:34.
 */
public class ConfigUpdate {
    public static final String PATH = "/config";

    private ActiveKeyValueStore store;

    private Random random = new Random();

    public ConfigUpdate(String hosts){
        store = new ActiveKeyValueStore();
        store.connect(hosts);
    }

    public void run() throws KeeperException, InterruptedException {
        while (true) {
            String value = random.nextInt(100) + "";
            store.write(PATH, value);
            System.out.println("Set " + PATH + " to " + value);
            try {
                TimeUnit.SECONDS.sleep(random.nextInt(10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) throws KeeperException, InterruptedException {
        ConfigUpdate configUpdate = new ConfigUpdate("localhost");
        configUpdate.run();
    }
}
