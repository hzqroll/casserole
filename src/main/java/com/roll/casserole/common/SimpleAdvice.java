package com.roll.casserole.common;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author zongqiang.hao
 * created on 2019-03-09 20:19.
 */
public class SimpleAdvice implements MethodInterceptor {
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("start: " + invocation.getMethod().getName());
        Object retVal = invocation.proceed();
        System.out.println("end");
        return retVal;
    }
}
