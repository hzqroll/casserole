package com.roll.casserole.jvm.classloader;

/**
 * @author roll
 * created on 2019-10-13 11:28
 */
public class MyTest1 {
    public static void main(String[] args) {
        System.out.println(MyChild1.str );
    }
}


class MyParent1 {
    public static String str = "hello world!";

    static {
        System.out.println("Myparent static block");
    }
}

class MyChild1 extends MyParent1 {

    public static String str2 = "welcome";

    static {
        System.out.println("MyChild static block");
    }
}