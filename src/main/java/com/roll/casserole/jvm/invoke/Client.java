package com.roll.casserole.jvm.invoke;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author roll
 * created on 2020/3/25 8:51 上午
 */
public class Client {
    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        RealSubject rs = new RealSubject();
        InvocationHandler ds = new DynamicSubject(rs);

        Class<?> cls = rs.getClass();

        Subject subject = (Subject) Proxy.newProxyInstance(cls.getClassLoader(), cls
                .getInterfaces(), ds);

        subject.request();

        System.out.println(subject.getClass());
        System.out.println(subject.getClass().getSuperclass());
    }
}
