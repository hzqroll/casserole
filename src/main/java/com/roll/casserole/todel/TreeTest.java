package com.roll.casserole.todel;

/**
 * <p>@author roll
 * <p>created on 2020/9/16 8:46 下午
 */
public class TreeTest {
    private static boolean flag = true;

    public static boolean charge(Node left, Node right) {
        System.out.println("charge");
        if (left == null && right == null) {
            return true;
        }
        if (left == null) {
            return false;
        }
        if (right == null) {
            return false;
        }
        if (left.value != right.value) {
            return false;
        }
        return charge(left.left, right.right) && charge(left.right, right.left);
    }

    public static void main(String[] args) {
        Node node = new Node(1);
        Node node21 = new Node(2);
        Node node22 = new Node(2);
        Node node31 = new Node(2);
        Node node32 = new Node(4);
        Node node33 = new Node(4);
        Node node34 = new Node(3);
        node.left = node21;
        node.right = node22;
        node21.left = node31;
        node21.right = node32;
        node22.left = node33;
        node22.right = node34;
        System.out.println(charge(node.left, node.right));
    }
}

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }


}
