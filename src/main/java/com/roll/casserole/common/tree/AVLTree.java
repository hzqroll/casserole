package com.roll.casserole.common.tree;

/**
 * AVL树🌲结点定义
 *
 * @author roll
 * created on 2020/4/2 11:32 上午
 */
public class AVLTree<T extends Comparable<T>> {

    /**
     * 在结点X的左孩子结点的左子树中插入元素
     * ll情况，
     *
     * @param x 失衡点
     * @return w平衡点
     */
    public AVLNode<T> llRoute(AVLNode<T> x) {
        // 拿到x结点的左节点l
        AVLNode<T> w = x.leftNode;
        // l的右结点变为a的左节点
        x.leftNode = w.rightNode;
        // x的左节点开始移动，x变为他的右节点
        w.rightNode = x;
        return w;
    }

    /**
     * 在结点X的右孩子结点的右子树中插入元素
     *
     * @param x 失衡点
     * @return w平衡点
     */
    public AVLNode<T> rrRoute(AVLNode<T> x) {
        AVLNode<T> w = x.rightNode;
        x.rightNode = w.leftNode;
        w.leftNode = x;
        return w;
    }

    /**
     * 在结点X的左孩子结点的右子树中插入元素
     *
     * @param x 失衡点
     * @return 平衡点
     */
    public AVLNode<T> lrRoute(AVLNode<T> x) {
        x.leftNode = rrRoute(x.leftNode);
        return llRoute(x);
    }

    /**
     * 在结点X的右孩子结点的左子树中插入元素
     *
     * @param x 失衡点
     * @return w平衡点
     */
    public AVLNode<T> rlRoute(AVLNode<T> x) {
        x.rightNode = llRoute(x.rightNode);
        return rrRoute(x);
    }

    /**
     * 新增节点操作
     *
     * @param data 新增数据
     * @param p    当前结点（从根结点开始寻找）
     * @return 新的当前结点
     */
    public AVLNode<T> insert(T data, AVLNode<T> p) {
        if (p == null) {
            p = new AVLNode<T>(data);
        } else if (data.compareTo(p.data) < 0) {// 是否小于p的data
            // 交给左子树来插入
            p.leftNode = insert(data, p.leftNode);
            // 如果差距等于2，进行重新平衡
            if (height(p.leftNode) - height(p.rightNode) == 2) {
                // 是否是插入左子树的
                if (data.compareTo(p.leftNode.data) < 0) {
                    p = llRoute(p);
                } else {
                    p = lrRoute(p);
                }
            }
        } else if (data.compareTo(p.data) > 0) {
            // 交给右子树来插入
            p.rightNode = insert(data, p.rightNode);
            if (height(p.rightNode) - height(p.leftNode) == 2) {
                if (data.compareTo(p.rightNode.data) < 0) {
                    p = rrRoute(p);
                } else {
                    p = rlRoute(p);
                }
            }
        }  // do nothing

        //重新计算各个结点的高度
        p.height = Math.max(height(p.leftNode), height(p.rightNode)) + 1;
        return p;
    }

    /**
     * 删除结点操作
     *
     * @param data 需要删除数据
     * @param p    当前结点（从根结点开始寻找）
     * @return 新的当前结点
     */
    public AVLNode<T> remove(T data, AVLNode<T> p) {
        if (p == null) {
            return null;
        }
        if (data.compareTo(p.data) < 0) {
            p.leftNode = remove(data, p.leftNode);
            // 检查是否平衡
            if (height(p.rightNode) - height(p.leftNode) == 2) {
                // 判断旋转方式
                if (height(p.rightNode.leftNode) > height(p.rightNode.rightNode)) {
                    p = lrRoute(p);
                } else {
                    p = rrRoute(p);
                }
            }
        } else if (data.compareTo(p.data) > 0) {
            // 检查是否平衡
            p.rightNode = remove(data, p.rightNode);
            if (height(p.leftNode) - height(p.rightNode) == 2) {
                if (height(p.leftNode.rightNode) > height(p.leftNode.leftNode)) {
                    p.rightNode = lrRoute(p.rightNode);
                } else {
                    p.rightNode = llRoute(p.rightNode);
                }
            }
        } else if (p.rightNode != null && p.leftNode != null) {
            p.data = findMin(p.rightNode).data;
            p.rightNode = remove(p.data, p.rightNode);
        } else {
            //只有一个孩子结点或者只是叶子结点的情况
            p = (p.leftNode != null) ? p.leftNode : p.rightNode;
        }
        //更新高度值
        if (p != null)
            p.height = Math.max(height(p.leftNode), height(p.rightNode)) + 1;
        return p;
    }

    /**
     * 查找最小值结点
     *
     * @param p 当前节点
     * @return
     */
    private AVLNode<T> findMin(AVLNode<T> p) {
        if (p == null)//结束条件
            return null;
        else if (p.leftNode == null)//如果没有左结点,那么t就是最小的
            return p;
        return findMin(p.rightNode);
    }

    /**
     * @param p 当前节点
     * @return 结点高度
     */
    public int height(AVLNode<T> p) {
        return p == null ? -1 : p.height;
    }
}
