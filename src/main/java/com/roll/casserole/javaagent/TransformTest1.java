package com.roll.casserole.javaagent;

/**
 * @author roll
 * created on 2019-10-05 20:09
 */
public class TransformTest1 {
    private static final TransformTest1 INSTANCE = new TransformTest1();

    public void print() {
        System.out.println("print unuse");
    }

    public TransformTest1 getInstance() {
        return INSTANCE;
    }

}
