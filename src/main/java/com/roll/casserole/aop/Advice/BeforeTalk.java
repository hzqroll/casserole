package com.roll.casserole.aop.Advice;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author zongqiang.hao
 * created on 2019-03-21 20:41.
 */
public class BeforeTalk implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("before talk!");
    }
}
