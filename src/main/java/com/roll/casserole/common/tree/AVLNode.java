package com.roll.casserole.common.tree;

/**
 * 平衡二叉树结点
 *
 * @author roll
 * created on 2020/4/3 11:09 上午
 */
public class AVLNode<T extends Comparable> {
    /**
     * 左节点
     */
    public AVLNode<T> leftNode;

    /**
     * 右结点
     */
    public AVLNode<T> rightNode;

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

    public AVLNode(T data) {
        this(null, null, data);
    }

    public AVLNode(AVLNode<T> leftNode, AVLNode<T> rightTree, T data) {
        this(null, null, data, 0);
    }

    public AVLNode(AVLNode<T> leftNode, AVLNode<T> rightNode, T data, int height) {
        this.leftNode = leftNode;
        this.rightNode = rightNode;
        this.data = data;
        this.height = height;
    }
}
