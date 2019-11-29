package com.roll.casserole.jvm.classloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author roll
 * created on 2019-11-19 08:38
 */
public class MyTest21 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        MyTest16 loader1 = new MyTest16("loader1");
        MyTest16 loader2 = new MyTest16("loader2");

        loader1.setPath("/Users/haozongqiang/Downloads/temp/");
        loader2.setPath("/Users/haozongqiang/Downloads/temp/");

        Class<?> clazz1 = loader1.loadClass("com.roll.casserole.jvm.classloader.MyPerson");
        Class<?> clazz2 = loader2.loadClass("com.roll.casserole.jvm.classloader.MyPerson");

        System.out.println(clazz1 == clazz2);// true, 都是被应用类加载器加载的

        Object object1 = clazz1.newInstance();
        Object object2 = clazz2.newInstance();

        Method method = clazz1.getMethod("setMyPerson", Object.class);
        method.invoke(object1, object2);

    }
}
