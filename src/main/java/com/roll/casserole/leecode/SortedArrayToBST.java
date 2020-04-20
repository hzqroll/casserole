package com.roll.casserole.leecode;

import java.util.ArrayList;
import java.util.List;

/**
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 * <p>
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 * <p>
 * 示例:
 * <p>
 * 给定有序数组: [-10,-3,0,5,9],
 * <p>
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 * <code>
 * 0
 * / \
 * -3   9
 * /   /
 * -10  5
 * </code>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * #108
 *
 * @author roll
 * created on 2020/4/2 2:02 下午
 */
public class SortedArrayToBST {
    int nums[];

    List<Integer> c = new ArrayList<>();

    public TreeNode helper(int left, int right) {
        if (left > right) return null;

        // always choose left middle node as a root
        int p = (left + right) / 2;

        // inorder traversal: left -> node -> right
        TreeNode root = new TreeNode(nums[p]);
        c.add(nums[p]);
        root.left = helper(left, p - 1);
        root.right = helper(p + 1, right);
        return root;
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    public TreeNode sortedArrayToBST(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int middle = (left + right) / 2;
        TreeNode treeNode = new TreeNode(nums[middle]);
        c.add(nums[middle]);
        treeNode.left = sortedArrayToBST(nums, left, middle - 1);
        treeNode.right = sortedArrayToBST(nums, middle + 1, right);
        return treeNode;
    }

    public static void main(String[] args) {
        SortedArrayToBST a = new SortedArrayToBST();
        int[] b = new int[]{-10, -3, 0, 5, 9};
        a.sortedArrayToBST(b);
        System.out.println(a.c);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                ", left=" + left == null ? null : left.toString() +
                ", right=" + left == null ? null : right.toString() +
                '}';
    }
}
