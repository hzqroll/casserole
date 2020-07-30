package com.roll.casserole.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <p>@author roll
 * <p>created on 2020/7/29 10:35 下午
 */
public class ProxyDemo {
    public static void main(String[] args) {
        //Proxy.getProxyClass();
    }
}

class InvocationHandlerDemo implements InvocationHandler{

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }
}
