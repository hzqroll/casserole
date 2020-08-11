package com.roll.casserole.jvm.classloader;

/**
 * @author roll
 * created on 2019-10-13 14:51
 *
 * <pre>
 *     Compiled from "MyTest2.java"
 * public class com.roll.casserole.jvm.classloader.MyTest2 {
 *   public com.roll.casserole.jvm.classloader.MyTest2();
 *     Code:
 *        0: aload_0
 *        1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 *        4: return
 *
 *   public static void main(java.lang.String[]);
 *     Code:
 *        0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
 *        3: ldc           #4                  // String hello world
 *        5: invokevirtual #5                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
 *        8: return
 * }
 * </pre>
 */
public class MyTest2 {
    public static void main(String[] args) {
        // hello world
        System.out.println(MyChild2.i);
    }
}

class MyParent2 {

    public static final String srr = "hello world";

    public static final String[] a = new String[10];

    public static final int i = 100;

    static {
        System.out.println("MyParent2 static block");
    }
}

class MyChild2 extends MyParent2 {
    public static final int i1 = 100;
}
