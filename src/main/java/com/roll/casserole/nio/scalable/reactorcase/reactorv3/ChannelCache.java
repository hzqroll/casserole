package com.roll.casserole.nio.scalable.reactorcase.reactorv3;

import java.nio.channels.SelectionKey;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 当前连接的SelectionKey集合
 *
 * @author roll
 * created on 2019-11-02 15:23
 */
public final class ChannelCache {
    private static final Set<SelectionKey> selectionKeySet = new HashSet<>();

    /**
     * 返回所有建立连接的SelectionKey
     *
     * @return Set
     */
    public static Set<SelectionKey> getActiveClient() {
        return selectionKeySet;
    }

    /**
     * 塞入一个建立连接的SelectionKey
     *
     * @param selectionKey selectionKey
     */
    public static void setSelectionKeyMap(SelectionKey selectionKey) {
        selectionKeySet.add(selectionKey);
    }

    private static final Map<SelectionKey, String> selectionKeyMap = new ConcurrentHashMap<>();

    public static void addSelectionKey(SelectionKey selectionKey, String name) {
        selectionKeyMap.put(selectionKey, name);
    }

    /**
     * 获取名称
     *
     * @param selectionKey selectionKey
     * @return 对应名称
     */
    public static String getName(SelectionKey selectionKey) {
        return selectionKeyMap.get(selectionKey);
    }
}
