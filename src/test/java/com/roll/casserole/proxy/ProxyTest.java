package com.roll.casserole.proxy;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

/**
 * @author zongqiang.hao
 * created on 2019-03-02 12:36.
 */
public class ProxyTest {
    @Test
    public void getProxyClassTest() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        IronInvokeProxy handler = new IronInvokeProxy(new SliverIron());
        Class proxyCLass = Proxy.getProxyClass(SliverIron.class.getClassLoader(), Iron.class);
        Iron iron = (Iron) proxyCLass.getConstructor(new Class[]{InvocationHandler.class}).newInstance(handler);
        iron.print("123");
    }

    @Test
    public void newProxyInstanceTest() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        IronInvokeProxy handler = new IronInvokeProxy(new SliverIron());
        Iron proxyClass = (Iron) Proxy.newProxyInstance(SliverIron.class.getClassLoader(), new Class[]{Iron.class}, handler);
        proxyClass.print("2232");
    }
}
