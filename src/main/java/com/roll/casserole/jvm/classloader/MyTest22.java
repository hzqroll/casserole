package com.roll.casserole.jvm.classloader;

/**
 * @author roll
 * created on 2019-11-21 08:57
 */
public class MyTest22 {

    static {
        System.out.println("MyTest22 initializer");
    }

    public static void main(String[] args) {
        System.out.println(MyTest22.class.getClassLoader());

        System.out.println(MyTest1.class.getClassLoader());
    }
}
