package com.roll.casserole.aop.Advice;

import org.springframework.aop.BeforeAdvice;

import java.lang.reflect.Method;

/**
 * 模拟before advice
 *
 * @author zongqiang.hao
 * created on 2019-03-10 13:02.
 */
public interface BeforeAdviceTest extends BeforeAdvice {
    void before(Method method, Object[] args, Object target) throws Throwable;
}
