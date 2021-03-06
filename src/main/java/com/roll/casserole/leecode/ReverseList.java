package com.roll.casserole.leecode;

import com.roll.casserole.leecode.ReverseList.ListNode;

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

    public static ListNode reverseList1(ListNode head) {
        ListNode current = head;
        ListNode a = current.next;
        head.next = null;
        ListNode tail = null;
        while (a.next != null) {
            ListNode b = a.next;
            a.next = current;
            current = a;
            tail = a;
            a = b;
        }
        a.next = tail;
        tail = a;
        return tail;
    }

    public static ListNode reverseList2(ListNode head) {
        ListNode current = head;
        ListNode a = current.next;
        head.next = null;
        ListNode tail = null;
        while (a != null) {
            ListNode b = a.next;
            a.next = current;
            current = a;
            tail = a;
            a = b;
        }
        return tail;
    }

    public static ListNode reverseList3(ListNode head) {
        // 前一个节点
        ListNode pre = null;
        // 当前节点
        ListNode current = head;
        // 只要当前节点还存在，就继续处理
        while (current != null) {
            //获取当前节点的下一个节点
            ListNode next = current.next;
            // 当前节点的下一个节点指向前一个节点（反转）
            current.next = pre;
            // pre和current移动一位
            pre = current;
            current = next;
        }
        // 返回当前节点
        return pre;
    }

    public static ListNode reverseListWithRecursion1(ListNode head) {
        // 1 结束条件
        if (head == null || head.next == null) {
            // 返回尾节点
            return head;
        }
        // 2 本级需要做的事情
        ListNode next = reverseListWithRecursion1(head.next);
        // 2.1 下个节点的next指向本级节点
        head.next.next = head;
        // 2.2 移除本级节点的next
        head.next = null;
        // 3 返回数据，需要返回本级节点，对于上层来说也就是下一个节点
        return next;
    }


    public static int rev(int a) {
        if (a < 0) {
            System.out.println(a);
            return a;
        } else {
            a = a - 1;
            rev(a--);
            System.out.println(a);
            return a;
        }

    }

    public static ListNode reverseListWithRecursion(ListNode head) {
        // 判断结束条件，如果是最后一个，直接返回
        if (head == null || head.next == null) {
            return head;
        }

        // 1 拿到下一个节点，如果下一个节点是最后一个，直接返回
        ListNode tail = reverseListWithRecursion(head.next);
        //2 当前节点下一个节点
        ListNode next = head.next;
        // 3  next的next指向前一个
        next.next = head;
        // 前一个的next设置为null
        head.next = null;
        // 返回下一个节点，从下一个节点开始计算
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
        a();
        b();
        c();
        rev(5);
        d();
    }

    public static void a() {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        listNode1.next = listNode2;
        ListNode listNode3 = new ListNode(3);
        listNode2.next = listNode3;
        ListNode listNode4 = new ListNode(4);
        listNode3.next = listNode4;
        reverseList(listNode1);
    }

    public static void b() {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        listNode1.next = listNode2;
        ListNode listNode3 = new ListNode(3);
        listNode2.next = listNode3;
        ListNode listNode4 = new ListNode(4);
        listNode3.next = listNode4;
        reverseList2(listNode1);
    }

    public static void c() {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        listNode1.next = listNode2;
        ListNode listNode3 = new ListNode(3);
        listNode2.next = listNode3;
        ListNode listNode4 = new ListNode(4);
        listNode3.next = listNode4;
        System.out.println(reverseList3(listNode1));
    }

    public static void d() {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        listNode1.next = listNode2;
        ListNode listNode3 = new ListNode(3);
        listNode2.next = listNode3;
        ListNode listNode4 = new ListNode(4);
        listNode3.next = listNode4;
        ListNode result = reverseListWithRecursion1(listNode1);
        System.out.println(result);
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */


}
