package com.roll.casserole.leecode;

/**
 * @author roll
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * <p>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例：
 * <p>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * <p>
 * created on 2019-11-29 17:24
 */
public class Solution2 {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {


        ListNode result = new ListNode(0);

        int plus = 0;

        ListNode current = result;

        while (l1 != null || l2 != null || plus > 0) {

            int a1 = l1 != null ? l1.val : 0;

            int b1 = l2 != null ? l2.val : 0;

            int sum = a1 + b1 + plus;

            int s1 = sum % 10;
            plus = sum / 10;

            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;

            current.next = new ListNode(s1);

            current = current.next;

        }
        System.out.println(result.next.toString());
        return result.next;
    }

    public static ListNode addTwoNumbers(ListNode r1, ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);

        int a1 = 0;
        if (l1 != null) {
            a1 = l1.val;
        }

        int b1 = 0;
        if (l2 != null) {
            b1 = l2.val;
        }

        int sum = a1 + b1 + r1.val;

        int s1 = sum % 10;
        int s2 = sum / 10;

        result.val = s1;

        ListNode l11 = null;
        if (l1 != null && l1.next != null) {
            l11 = l1.next;
        }

        ListNode l21 = null;
        if (l2 != null && l2.next != null) {
            l21 = l2.next;
        }

        // 给r1赋值
        r1 = result;

        // 需要r1的下一个节点，且不能断
        if (l11 != null && l21 != null) {
            r1.next = new ListNode(s2);
            addTwoNumbers(r1.next, l11, l21);
        }

        System.out.println(r1.toString());
        return r1;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(5);
        ListNode a12 = new ListNode(4);
        l1.next = a12;
        a12.next = new ListNode(3);
        System.out.println(l1.toString());

        ListNode l2 = new ListNode(5);
        /*ListNode a15 = new ListNode(6);
        l2.next = a15;
        a15.next = new ListNode(4);
        System.out.println(l2.toString());*/


        ListNode l3 = addTwoNumbers(l1, l2);
        System.out.println(l3.toString());
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }
}


