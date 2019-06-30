package com.roll.casserole.zookeeper.sonfigurationservice;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * @author zongqiang.hao
 * created on 2018/10/2 下午10:14.
 */
public class ConfigWatcher implements Watcher {
    private ActiveKeyValueStore store;

    public ConfigWatcher(String hosts) {
        store = new ActiveKeyValueStore();
        store.connect(hosts);
    }

    public void displayConfig() {
        String value = store.read(ConfigUpdate.PATH, this);
        System.out.println("read " + ConfigUpdate.PATH + "as " + value);
    }

    @Override
    public void process(WatchedEvent event) {
        if (event.getType() == Event.EventType.NodeDataChanged) {
            displayConfig();
        }
    }

    public static void main(String args[]){
        ConfigWatcher configWatcher = new ConfigWatcher("localhost");
        configWatcher.displayConfig();

    }
}
