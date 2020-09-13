package com.roll.casserole.leecode;

import java.util.*;

/**
 * <p>@author roll
 * <p>created on 2020/9/2 5:17 下午
 */
public class LRUCache {
    Map<Object, Object> cache = new HashMap<>();

    DLinkedNode head = null;
    DLinkedNode tail = null;

    public LRUCache() {
    }

    public void setCache(Object key, Object value) {
        DLinkedNode dLinkedNode = new DLinkedNode(key);

    }

    public void addHeadNode(DLinkedNode dLinkedNode) {
        head.addPrev(dLinkedNode);
        head = dLinkedNode;
    }

    /**
     * 获取尾部前一个节点，移动tail节点位置，删除map缓存内容
     */
    public void removeUnused() {
        DLinkedNode dLinkedNode = tail.getPrev();
        Object key = dLinkedNode.getKey();
        tail = dLinkedNode;
        cache.remove(key);
    }
}

class DLinkedNode {
    private final Object key;

    private DLinkedNode prev;
    private DLinkedNode next;

    public Object getKey() {
        return key;
    }

    public DLinkedNode(Object key) {
        this.key = key;
    }

    public DLinkedNode getPrev() {
        return prev;
    }

    public void setPrev(DLinkedNode prev) {
        this.prev = prev;
    }

    public DLinkedNode getNext() {
        return next;
    }

    public void setNext(DLinkedNode next) {
        this.next = next;
    }

    public void addNext(DLinkedNode dLinkedNode) {
        this.next = dLinkedNode;
    }

    public void addPrev(DLinkedNode dLinkedNode) {
        this.prev = dLinkedNode;
    }
}
