package com.roll.casserole.jvm.classloader;

/**
 * @author roll
 * created on 2019-11-17 20:58
 */
public class MyTest18_1 {
    public static void main(String[] args) throws ClassNotFoundException {
        MyTest16 loader1 = new MyTest16("loader1");
        loader1.setPath("/Users/haozongqiang/Downloads/temp/");

        Class<?> clazz = loader1.loadClass("com.roll.casserole.jvm.classloader.MyTest1");

        System.out.println("class: " + clazz.hashCode());

        System.out.println("class loader: " + clazz.getClassLoader());

    }
}
