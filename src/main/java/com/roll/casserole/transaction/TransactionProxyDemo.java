package com.roll.casserole.transaction;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import java.lang.reflect.Method;

/**
 * @author zongqiang.hao
 * created on 2019-04-11 09:23.
 */
public class TransactionProxyDemo implements MethodInterceptor {

    private PlatformTransactionManager transactionManager;

    public TransactionProxyDemo(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        TransactionDefinition definition = getTransactionDefinitionFromMethod(method);
        TransactionStatus status = transactionManager.getTransaction(definition);
        Object result;
        try {
            result = invocation.proceed();
        } catch (Throwable throwable) {
            if (needRollbackOn(throwable)) {
                transactionManager.rollback(status);
            } else {
                transactionManager.commit(status);
            }
            throw throwable;
        }
        transactionManager.commit(status);
        return result;
    }

    private TransactionDefinition getTransactionDefinitionFromMethod(Method method) {
        return null;
    }

    private boolean needRollbackOn(Throwable t) {
        return false;
    }
}
