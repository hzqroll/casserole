package com.roll.casserole.proxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zongqiang.hao
 * created on 2019-02-28 15:46.
 */
public class ProxyTest {
    public static void main(String[] args) throws Throwable {
        SliverIron sliverIron = new SliverIron();
        IronInvokeProxy<Iron> ironIronInvokeProxy = new IronInvokeProxy<>(sliverIron);
        Method method = sliverIron.getClass().getMethod("print", String.class);
        Object[] objects = new Object[1];
        objects[0] = "打印";
        ironIronInvokeProxy.invoke(ironIronInvokeProxy, method, objects);

        Iron proxy = (Iron) Proxy.newProxyInstance(sliverIron.getClass().getClassLoader(), sliverIron.getClass().getInterfaces(), ironIronInvokeProxy);
        proxy.print("proxy");

        Class<?> proxy1 = Proxy.getProxyClass(sliverIron.getClass().getClassLoader(), Iron.class);
        System.out.println(proxy1.getName());
        Iron  iron = (Iron) proxy1.getConstructor(void.class).newInstance();
        iron.print("12");
    }
}
