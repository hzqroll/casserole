package com.roll.casserole.design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author zongqiang.hao
 * created on 2019-02-21 15:26.
 */
public class MyInvocationHandler implements InvocationHandler {

    private Dog dog;

    public MyInvocationHandler(Dog dog) {
        this.dog = dog;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("start time: " + System.currentTimeMillis());
        method.invoke(dog, args);
        System.out.println("end time: " + System.currentTimeMillis());
        return null;
    }
}
