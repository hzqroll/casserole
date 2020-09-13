package com.roll.casserole.leecode;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>@author roll
 * <p>created on 2020/9/1 10:21 下午
 */
public class ReverseList {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    public static ListNode reverseList(ListNode head) {
        ListNode tail = null;
        ListNode currNode = head;
        while (currNode != null) {
            ListNode temp = currNode.next;
            tail = currNode;
            currNode.next = tail;
            currNode = temp;
        }
        return tail;
    }

    public static ListNode reverse(ListNode tail) {
        if (tail == null || tail.next == null) return tail;
        ListNode p = reverseList(tail.next);
        tail.next.next = tail;
        tail.next = null;
        return p;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        listNode1.next = listNode2;
        ListNode listNode3 = new ListNode(3);
        listNode2.next = listNode3;
        ListNode listNode4 = new ListNode(4);
        listNode3.next = listNode4;
        reverseList(listNode1);
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}
