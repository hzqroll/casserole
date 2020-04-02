package com.roll.casserole.common.tree;

/**
 * AVL树🌲结点定义
 *
 * @author roll
 * created on 2020/4/2 11:32 上午
 */
public class AVLTree<T extends Comparable<T>> {
    /**
     * 左节点
     */
    public AVLTree<T> leftNode;

    /**
     * 右结点
     */
    public AVLTree<T> rightTree;

    /**
     * 当前结点保存的数据
     */
    public T data;

    /**
     * 当前结点的高度
     * 高度是指当前结点到叶子结点的最长路径，而深度则是指从根结点到当前结点的最大路径
     * 如所有叶子结点的高度都为0，根结点的深度为0
     */
    public int length;

    public AVLTree(T data) {
        this(null, null, data);
    }

    public AVLTree(AVLTree<T> leftNode, AVLTree<T> rightTree, T data) {
        this(null, null, data, 0);
    }

    public AVLTree(AVLTree<T> leftNode, AVLTree<T> rightTree, T data, int length) {
        this.leftNode = leftNode;
        this.rightTree = rightTree;
        this.data = data;
        this.length = length;
    }

    /**
     * ll情况，
     */
    public void llRoute(AVLTree<T> x) {
        //把w结点旋转为根结点
        //AVLNode<T> w=  x.left;
        //同时w的右子树变为x的左子树
        //x.left=w.right;
        //x变为w的右子树
        //w.right=x;
        //重新计算x/w的高度
        //x.height=Math.max(height(x.left),height(x.right))+1;
        //w.height=Math.max(height(w.left),x.height)+1;
        //return w;//返回新的根结点
        // 拿到x结点的左节点l
        AVLTree<T> leftNode = x.leftNode;
        // x的左节点开始移动，x变为他的右节点
        leftNode.rightTree = x;
        // l的右结点变为a的左节点
        x.leftNode = leftNode.rightTree;
    }
}
