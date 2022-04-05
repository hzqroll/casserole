package com.roll.casserole.leecode;

import java.util.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * <p>@author roll
 * <p>created on 2020/10/10 2:26 下午
 */
public class Solver {
    //使用Queue实现BFS
    public void BFSWithQueue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);

        }
        int c = 0;
        while (!queue.isEmpty() && c == 0) {
            TreeNode treeNode = queue.poll();
            if (treeNode.right != null) {
                c = c + 1;
                queue.add(treeNode.right);
            }

            if (treeNode.left != null) {
                c = c + 1;
                queue.add(treeNode.left);
            }
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{1324101
                , 8
                , 1
                , 1
                , 8
                , 5
                , 8
                , 5
                , 8
                , 8
                , 8
                , 1
                , 1
                , 8
                , 8
                , 8
                , 8
                , 8
                , 8
                , 8
                , 5
                , 8
                , 5
                , 8
                , 15
                , 8
                , 5
                , 8
                , 5
                , 15
                , 8
                , 8
                , 1
                , 15
                , 8
                , 5
                , 2
                , 1329702
                , 8
                , 5
                , 8
                , 5
                , 2
                , 8
                , 8
                , 1
                , 1
                , 8
                , 1
                , 1
                , 1
                , 1
                , 8
                , 5
                , 1831501
                , 5
                , 8
                , 1831501
                , 1
                , 1
                , 15
                , 2
                , 8
                , 5
                , 8
                , 5
                , 8
                , 5
                , 8
                , 5
                , 8
                , 1
                , 1
                , 1
                , 1831501
                , 1
                , 1
                , 8
                , 8
                , 8
                , 2
                , 5
                , 8
                , 5
                , 8
                , 5
                , 8
                , 5
                , 8
                , 1831501
                , 1831501
                , 5
                , 1831501
                , 8
                , 8
                , 8
                , 8
                , 1831501
                , 1831501
                , 1
                , 1
                , 1
                , 8
                , 8
                , 5
                , 8
                , 5
                , 8
                , 1
                , 1
                , 1
                , 1
                , 8
                , 8
                , 8
                , 1
                , 5
                , 8
                , 8
                , 8
                , 8
                , 8
                , 2
                , 5
                , 8
                , 5
                , 8
                , 5
                , 8
                , 8
                , 8
                , 1831501
                , 8
                , 1831501
                , 15
                , 1
                , 1
                , 2
                , 8
                , 5
                , 8
                , 5
                , 8
                , 1
                , 1
                , 1
                , 8
                , 8
                , 5
                , 5
                , 5
                , 8
                , 8
                , 2
                , 8
                , 1831501
                , 1
                , 1
                , 8
                , 5
                , 2
                , 5
                , 8
                , 8
                , 8
                , 1
                , 1
                , 2
                , 5
                , 8
                , 8
                , 8
                , 8
                , 5
                , 8
                , 8
                , 8
                , 1831501
                , 1831501
                , 1
                , 1
                , 1
                , 8
                , 8
                , 8
                , 5
                , 8
                , 5
                , 8
                , 8
                , 1
                , 1
                , 5
                , 8
                , 5
                , 8
                , 8
                , 5
                , 8
                , 5
                , 2
                , 2
                , 8
                , 1
                , 8
                , 5
                , 8
                , 5
                , 8
                , 8
                , 8
                , 8
                , 1
                , 2
                , 2
                , 1
                , 1
                , 1
                , 1
                , 8
                , 8
                , 8
                , 5
                , 5
                , 8
                , 5
                , 8
                , 15
                , 1831501
                , 1
                , 1
                , 8
                , 1
                , 2
                , 5
                , 8
                , 1332314};
        Set s = new HashSet<>();
        for (int o : a) {
            s.add(o);
        }
        s.forEach(System.out::println);
    }
}
