package com.roll.casserole.jvm.gc;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * -XX:MaxMetaspaceSize=10M
 * <p>@author roll
 * <p>created on 2020/8/9 2:48 下午
 */
public class MyTest4 {
    public static void main(String[] args) {
        for (; ; ) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(MyTest4.class);
            enhancer.setUseCache(false);
            enhancer.setCallback((MethodInterceptor) (obj, method, args1, proxy) ->
                    proxy.invoke(obj, args));
            enhancer.create();
        }
    }
}
