package com.roll.casserole.common.tree;

/**
 * 树结点
 *
 * @author roll
 * created on 2020/4/3 11:09 上午
 */
public class TreeNode<T extends Comparable<T>> {
    /**
     * 左节点
     */
    public TreeNode<T> leftNode;

    /**
     * 右结点
     */
    public TreeNode<T> rightNode;

    /**
     * 当前结点保存的数据
     */
    public T data;

    /**
     * 当前结点的高度
     * 高度是指当前结点到叶子结点的最长路径，而深度则是指从根结点到当前结点的最大路径
     * 如所有叶子结点的高度都为0，根结点的深度为0
     */
    public int height;

    public TreeNode(T data) {
        this(null, null, data);
    }

    public TreeNode(TreeNode<T> leftNode, TreeNode<T> rightTree, T data) {
        this(null, null, data, 0);
    }

    public TreeNode(TreeNode<T> leftNode, TreeNode<T> rightNode, T data, int height) {
        this.leftNode = leftNode;
        this.rightNode = rightNode;
        this.data = data;
        this.height = height;
    }
}
