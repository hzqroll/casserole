package com.roll.casserole.common.tree;

/**
 * @author zongqiang.hao
 * created on 2020/4/7 9:29 下午.
 */
public class RedBlackTree {
    private final int R = 0;
    private final int B = 1;

    private Node root = null;// 根节点

    class Node {
        int data;
        int color = R;
        Node left;
        Node right;
        Node parent;

        public Node(int data) {
            this.data = data;
        }
    }

    public void insert(Node root, int data) {
        if (root.data < data) {
            if (root.right == null) {
                root.right = new Node(data);
            } else {
                insert(root.right, data);
            }
        } else {
            if (root.left == null) {
                root.left = new Node(data);
            } else {
                insert(root.left, data);
            }
        }
    }

    /**
     * 左旋
     */
    public void leftRotate(Node node) {
        if (node.parent == null) {
            Node E = root;
            Node S = E.right;
            // 移动S的左子树
            E.right = S.left;
            S.left.parent = E;
            // 修改E的指针
            E.parent = S;
            // 修改S的指针
            S.parent = null;
        }
    }
}
