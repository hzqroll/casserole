package com.roll.casserole.leecode;

import java.util.HashMap;
import java.util.Map;

/**
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 * <p>
 * 获取数据 get(key) - 如果关键字 (key) 存在于缓存中，则获取关键字的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字/值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 * <p>
 *  
 * <p>
 * 进阶:
 * <p>
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * LRUCache cache = new LRUCache( 2  缓存容量  );
 * <p>
 * cache.put(1,1);
 * cache.put(2,2);
 * cache.get(1);       // 返回  1
 * cache.put(3,3);    // 该操作会使得关键字 2 作废
 * cache.get(2);       // 返回 -1 (未找到)
 * cache.put(4,4);    // 该操作会使得关键字 1 作废
 * cache.get(1);       // 返回 -1 (未找到)
 * cache.get(3);       // 返回  3
 * cache.get(4);       // 返回  4
 * 通过次数99,144提交次数195,477
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lru-cache
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>@author roll
 * <p>created on 2020/9/13 10:36 上午
 * <p>
 * 如果超过容量，
 * 查询之后，需要
 */
public class LRUCacheSimple {
    int count = 0;
    int capacity;

    Map<Integer, DoubleNode> dataMap;
    DoubleNode head;
    DoubleNode tail;

    public LRUCacheSimple(int capacity) {
        this.capacity = capacity;
        this.dataMap = new HashMap<>(capacity);
    }

    /**
     * 找到数据，移动数据到头部的下一个
     */
    public int get(int key) {
        if (dataMap.get(key) != null) {
            moveToHead(dataMap.get(key));
            return dataMap.get(key).value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (count >= capacity) {
            removeTail();
        }
        DoubleNode doubleNode;
        if (dataMap.containsKey(key)) {
            doubleNode = dataMap.get(key);
        } else {
            doubleNode = new DoubleNode(key, value);
        }
        moveToHead(doubleNode);
        dataMap.put(key, doubleNode);
    }

    /**
     * 增加节点
     */
    void addNode(DoubleNode doubleNode) {
        doubleNode.next = head.next;
        doubleNode.previous = head;
    }

    /**
     * 删除节点
     */
    void removeNode(DoubleNode node) {
        node.previous.next = node.next;
        node.next.previous = node.previous;
    }

    void removeTail() {
        removeNode(tail.previous);
    }

    void moveToHead(DoubleNode node) {
        head.next.previous = node;
        node.next = head.next;
        node.previous = head;
    }
}

class DoubleNode {
    int key;
    int value;
    DoubleNode next;
    DoubleNode previous;

    public DoubleNode(int key, int value) {
        this.key = key;
        this.value = value;
    }
}


