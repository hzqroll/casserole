package com.roll.casserole.reflect;

import java.lang.reflect.Array;

/**
 * 利用反射创建数组
 *
 * @author zongqiang.hao
 * created on 2019-02-22 10:33.
 */
public class ArrayTest {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> clazz = Class.forName("com.roll.casserole.design.proxy.Pomeranian");
        /*Object array = Array.newInstance(clazz, 20);
        //往数组里添加内容
        Array.set(array,0,"hello");
        Array.set(array,1,"Java");
        Array.set(array,2,"fuck");
        Array.set(array,3,"Scala");
        Array.set(array,4,"Clojure");
        //获取某一项的内容
        System.out.println(Array.get(array,3));*/
    }
}
