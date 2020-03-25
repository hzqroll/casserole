package com.roll.casserole.jvm.invoke;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author roll
 * created on 2020/3/25 8:49 上午
 */
public class DynamicSubject implements InvocationHandler {
    private Object sub;

    public DynamicSubject(Object sub) {
        this.sub = sub;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before calling: " + method);

        method.invoke(this.sub, args);

        System.out.println("after calling: " + method);
        return null;
    }
}
