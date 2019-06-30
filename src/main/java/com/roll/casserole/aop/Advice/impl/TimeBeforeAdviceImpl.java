package com.roll.casserole.aop.Advice.impl;

import com.roll.casserole.aop.Advice.BeforeAdviceTest;

import java.lang.reflect.Method;

/**
 * @author zongqiang.hao
 * created on 2019-03-10 13:16.
 */
public class TimeBeforeAdviceImpl implements BeforeAdviceTest {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("ehllo");
    }
}
