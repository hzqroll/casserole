package com.roll.casserole.leecode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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
        if (root != null){
            queue.add(root);

        }
        int c = 0;
        while (!queue.isEmpty() && c == 0) {
            TreeNode treeNode = queue.poll();
            if (treeNode.right != null){
                c = c + 1;
                queue.add(treeNode.right);
            }

            if (treeNode.left != null){
                c = c + 1;
                queue.add(treeNode.left);
            }
        }
    }

    public static void main(String[] args) {

    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
