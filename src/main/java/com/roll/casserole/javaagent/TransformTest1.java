package com.roll.casserole.javaagent;

/**
 * @author roll
 * created on 2019-10-05 20:09
 */
public class TransformTest1 {
    public void print() {
        System.out.println("print unuse");
    }

    public static void main(String[] args) {
        new TransformTest1().print();
    }
}
