package com.roll.casserole.common.tree;

import java.util.List;

/**
 * 树接口
 *
 * @author roll
 * created on 2020/4/3 11:11 上午
 */
public interface Tree<T extends Comparable<T>> {
    /**
     * 插入数据
     */
    void insert(T data);

    /**
     * 移除数据
     */
    void remove(T data);

    /**
     * 前序遍历输出：根节点->左子树->右子树
     */
    void preOrderTraverse();

    /**
     * 后序便利输出：左子树->右子树->跟节点
     */
    void postOrderTraverse();

    /**
     * 中序便利输出：左子树->跟节点->右子树
     */
    void middleOrderTraverse();

    /**
     * 层次遍历
     */
    void levelOrderTraverse();

    /**
     * 深度优先遍历
     *
     */
    void depthOrderTraverse();

    /**
     * 查找最大值
     */
    T findMin();

    /**
     * 查找最小值
     */
    T findMax();

    default void preOrderTraverse(TreeNode<T> node) {
        if (node != null) {
            System.out.println(node.data + " ");
            preOrderTraverse(node.leftNode);
            preOrderTraverse(node.rightNode);
        }
    }

    default void postOrderTraverse(TreeNode<T> node) {
        if (node != null) {
            preOrderTraverse(node.leftNode);
            preOrderTraverse(node.rightNode);
            System.out.println(node.data + " ");
        }
    }

    default void middleOrderTraverse(TreeNode<T> node) {
        if (node != null) {
            preOrderTraverse(node.leftNode);
            System.out.println(node.data + " ");
            preOrderTraverse(node.rightNode);
        }
    }

    default void levelOrderTraverse(List<TreeNode<T>> nodeList) {
        nodeList.forEach(node -> {
            if (node != null) {
                System.out.println(node.data);
                nodeList.remove(node);
                if (node.leftNode != null) {
                    nodeList.add(node.leftNode);
                }
                if (node.rightNode != null) {
                    nodeList.add(node.rightNode);
                }
            }
        });
        levelOrderTraverse(nodeList);
    }
}
