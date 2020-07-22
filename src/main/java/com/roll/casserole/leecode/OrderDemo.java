package com.roll.casserole.leecode;

import java.util.Arrays;

/**
 * <p>@author roll
 * <p>created on 2020/7/2 10:30 下午
 */
public class OrderDemo {
    /**
     * 冒泡排序，每次移动最大到末尾
     */
    public static int[] bubblingOrder(int[] arr) {
        for (int i = 0; i < arr.length - 1 - i; i++) {
            int j = 0;
            while (j < arr.length - 1 - i) {
                if (arr[j] > arr[j + 1]) {
                    int c = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = c;
                }
                j++;
            }
        }
        return arr;
    }

    /**
     * 归并排序
     */
    public static void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    public static void mergeSort(int[] arrays, int L, int R) {
        //如果只有一个元素，那就不用排序了
        if (L == R) {
            return;
        } else {
            //取中间的数，进行拆分
            int M = (L + R) / 2;
            //左边的数不断进行拆分
            mergeSort(arrays, L, M);
            //右边的数不断进行拆分
            mergeSort(arrays, M + 1, R);
            //合并
            merge(arrays, L, M + 1, R);
        }
    }

    public static void merge(int[] arrays, int L, int M, int R) {
        //左边的数组的大小
        int[] leftArray = new int[M - L];
        //右边的数组大小
        int[] rightArray = new int[R - M + 1];
        //往这两个数组填充数据
        for (int i = L; i < M; i++) {
            leftArray[i - L] = arrays[i];
        }
        for (int i = M; i <= R; i++) {
            rightArray[i - M] = arrays[i];
        }
        int i = 0, j = 0;
        // arrays数组的第一个元素
        int k = L;

        //比较这两个数组的值，哪个小，就往数组上放
        while (i < leftArray.length && j < rightArray.length) {
            //谁比较小，谁将元素放入大数组中,移动指针，继续比较下一个
            if (leftArray[i] < rightArray[j]) {
                arrays[k] = leftArray[i];
                i++;
            } else {
                arrays[k] = rightArray[j];
                j++;
            }
            k++;
        }

        //如果左边的数组还没比较完，右边的数都已经完了，那么将左边的数抄到大数组中(剩下的都是大数字)
        while (i < leftArray.length) {
            arrays[k] = leftArray[i];
            i++;
            k++;
        }
        //如果右边的数组还没比较完，左边的数都已经完了，那么将右边的数抄到大数组中(剩下的都是大数字)
        while (j < rightArray.length) {
            arrays[k] = rightArray[j];
            k++;
            j++;
        }
    }

    public static void main(String[] args) {

        //System.out.println(Arrays.toString(bubblingOrder(new int[]{11, 22, 3, 4, 15})));
        int[] a = new int[]{11, 22, 3, 4, 15};
        mergeSort(a);
        System.out.println(Arrays.toString(a));

        Integer[] b = new Integer[]{3, 5, 31, 42, 55};
        System.out.println(getIndex(b, 66));
//        sort(a);
//        System.out.println(Arrays.toString(a));
//
//        //-1->5->3->4->0
//        ListNode head = new ListNode(-1).setNext(new ListNode(5).setNext(new ListNode(3).setNext(new ListNode(4))));
//
//        sortList(head);
//        System.out.println("1");


    }

    public static void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    public static void sort(int[] arr, int l, int r) {
        if (l != r) {
            int mid = (l + r) / 2;
            sort(arr, l, mid);
            sort(arr, mid + 1, r);
            mergeMy(arr, l, mid, r);
        } else {
            return;
        }
    }

    /**
     * 两个数组合并
     *
     * @param arr 数组
     * @param l   左边
     * @param m   中位
     * @param r   右边
     */
    public static void mergeMy(int[] arr, int l, int m, int r) {
        // 左边数组内容
        int[] leftArray;
        leftArray = Arrays.copyOfRange(arr, l, m + 1);
        // 右边数组内容
        int[] rightArray;
        rightArray = Arrays.copyOfRange(arr, m + 1, r + 1);

        int i = 0, j = 0;
        // 数组合并, 排序后合并
        while (i < leftArray.length && j < rightArray.length) {
            if (leftArray[i] > rightArray[j]) {
                arr[l++] = rightArray[j];
                j++;
            } else {
                arr[l++] = leftArray[i];
                i++;
            }
        }
        // 此时left剩下的都是left中比较大的，但是比right小
        while (i < leftArray.length) {
            arr[l++] = leftArray[i++];
        }
        // 此时right剩下的都是比较大的
        while (j < rightArray.length) {
            arr[l++] = rightArray[j++];
        }
    }

    /**
     * 对list进行排序
     *
     * @param head 头节点
     * @return 头节点
     */
    public static ListNode sortList(ListNode head) {
        return head == null ? null : mergeSortList(head);
    }

    private static ListNode mergeSortList(ListNode head) {
        // 如果只有一个节点，直接返回，不需要比较
        if (head.next == null) {
            return head;
        }
        // 利用快慢节点，来进行切割
        // pre的作用是记录slow的前一个位置，用来分割两个链表，下一个链表的位置从slow开始
        ListNode slow = head, fast = head, pre = null;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        pre.next = null;
        ListNode l = mergeSortList(head);
        ListNode r = mergeSortList(slow);
        return merge(l, r);
    }

    static ListNode merge(ListNode l, ListNode r) {
        // 虚拟出一个头节点
        ListNode dummyHead = new ListNode(0);
        // cur位置
        ListNode cur = dummyHead;
        while (l != null && r != null) {
            if (l.val <= r.val) {
                // cur的next是l
                cur.next = l;
                // cur 移动以为，也就是刚刚的l
                cur = cur.next;
                // l移动一位
                l = l.next;
            } else {
                cur.next = r;
                cur = cur.next;
                r = r.next;
            }
        }
        // 最后给cur的next
        if (l != null) {
            cur.next = l;
        }
        // 最后给cur的next
        if (r != null) {
            cur.next = r;
        }
        // 虚拟节点的拼接的链表
        return dummyHead.next;
    }

    public static int getIndex(Integer[] arr, int target) {
        return getIndex(arr, 0, arr.length, target);
    }

    public static int getIndex(Integer[] arr, int start, int end, int target) {
        if (start == end) {
            if (end == arr.length) {
                return 0;
            }
            return start + 1;
        }
        int mid = (start + end) / 2;
        if (arr[mid] < target) {
            return getIndex(arr, mid + 1, end, target);
        } else if (arr[mid] > target) {
            return getIndex(arr, start, mid, target);
        } else {
            return mid;
        }
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    public ListNode setNext(ListNode next) {
        this.next = next;
        return this;
    }
}
