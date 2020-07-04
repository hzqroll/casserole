package com.roll.casserole.leecode;

/**
 * 快慢指针案例
 * <p>@author roll
 * <p>created on 2020/7/1 1:51 下午
 * <a href="https://juejin.im/post/5b46a7c75188251a8d36d482"> 快慢节点介绍</a>
 */
public class SlowAndFastPoint {

    /**
     * 输入一个单链表的头结点，输出该链表中倒数第 k 个节点的值
     */
    public int getKEle(LinkNode linkNode, int k) {
        int fastCount = 0;
        int slowCount = 0;
        LinkNode fastNode = linkNode;
        LinkNode slowNode = linkNode;
        while (slowNode != null) {
            // fastNode没到达底部的时候，快节点移动
            if (fastNode != null && fastNode.getNext() != null) {
                fastNode = fastNode.getNext().getNext();
                fastCount = fastCount + 2;
            } else {
                if (fastCount - slowCount == k) {
                    return slowNode.getData();
                }
                if (fastCount - slowCount == k + 1) {
                    return slowNode.getNext().getData();
                }
            }
            // 快慢节点差距k之前，慢节点先不动
            if (fastCount - slowCount >= k) {
                slowNode = slowNode.getNext();
                slowCount = slowCount + 1;
            }
        }
        return 0;
    }
}

class LinkNode {

    private int data;
    private LinkNode next;

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public LinkNode getNext() {
        return next;
    }

    public void setNext(LinkNode next) {
        this.next = next;
    }
}