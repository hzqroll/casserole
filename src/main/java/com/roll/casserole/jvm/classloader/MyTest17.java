package com.roll.casserole.jvm.classloader;

/**
 * @author roll
 * created on 2019-11-17 14:27
 */
public class MyTest17 {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        MyTest16 loader = new MyTest16("loader1");

        Class<?> clazz = loader.loadClass("com.roll.casserole.jvm.classloader.MySample");
        System.out.println("class: " + clazz.hashCode());

        Object object = clazz.newInstance();
        System.out.println(object);
        //class: 777874839
        //MySample is loaded by: sun.misc.Launcher$AppClassLoader@18b4aac2
        //MyCat is loaded by: sun.misc.Launcher$AppClassLoader@18b4aac2
        //com.roll.casserole.jvm.classloader.MySample@238e0d81
    }
}
