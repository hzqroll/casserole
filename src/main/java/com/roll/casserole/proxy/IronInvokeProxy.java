package com.roll.casserole.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author zongqiang.hao
 * created on 2019-02-27 20:20.
 */
public class IronInvokeProxy<SliverIron> implements InvocationHandler {

    private com.roll.casserole.proxy.SliverIron object;

    public IronInvokeProxy() {
    }

    public IronInvokeProxy(com.roll.casserole.proxy.SliverIron object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("start");
        method.invoke(object, args);
        System.out.println("end");
        return null;
    }
}
