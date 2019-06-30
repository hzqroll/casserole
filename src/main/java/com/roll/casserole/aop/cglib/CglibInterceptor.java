package com.roll.casserole.aop.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author zongqiang.hao
 * created on 2019-03-21 08:49.
 */
public class CglibInterceptor implements MethodInterceptor {

    /**
     * @param o           目标对象
     * @param method      目标方法
     * @param objects     参数
     * @param methodProxy cglib方法代理对象
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib 调用前");
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("cglib 调用前");
        return result;
    }
}
