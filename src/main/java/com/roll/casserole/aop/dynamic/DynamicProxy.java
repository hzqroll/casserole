package com.roll.casserole.aop.dynamic;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @author zongqiang.hao
 * created on 2019-03-13 14:53.
 */
public class DynamicProxy implements InvocationHandler {

    private Object target;

    public DynamicProxy(Object target) {
        this.target = target;
    }

    public Object getProxy(InvocationHandler invocationHandler) {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), invocationHandler);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result;
        System.out.println(method.getName() + ", start, args: " + Arrays.toString(args) + " .");
        result = method.invoke(target, args);
        System.out.println(method.getName() + ", end, result: " + JSON.toJSONString(result) + " .");
        return result;
    }
}
