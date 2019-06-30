package com.roll.casserole.aop.Advice.impl;

import com.roll.casserole.aop.Advice.IntroductionTest;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.IntroductionInterceptor;
import org.springframework.stereotype.Service;

/**
 * @author zongqiang.hao
 * created on 2019-03-10 15:24.
 */
@Service
public class AdditionalImp implements IntroductionTest, IntroductionInterceptor {
    @Override
    public void additionFun() {
        System.out.println("添加的额外的功能.");
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        if (implementsInterface(invocation.getMethod().getDeclaringClass())) {
            return invocation.getMethod().invoke(this, invocation.getArguments());
        } else {
            return invocation.proceed();
        }
    }

    @Override
    public boolean implementsInterface(Class<?> intf) {
        return intf.isAssignableFrom(AdditionalImp.class);
    }
}
