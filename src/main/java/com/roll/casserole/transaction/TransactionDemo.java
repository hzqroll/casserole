package com.roll.casserole.transaction;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;

/**
 * @author zongqiang.hao
 * created on 2019-04-11 08:39.
 */
public class TransactionDemo {
    public void platformTransactionTest() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
        defaultTransactionDefinition.setTimeout(20);
        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(defaultTransactionDefinition);
        try {
            //事务操作1
        } catch (RuntimeException e) {
            dataSourceTransactionManager.rollback(transactionStatus);
        } catch (Error error) {
            dataSourceTransactionManager.rollback(transactionStatus);
        }
        dataSourceTransactionManager.commit(transactionStatus);
    }

    /**
     * TransactionTemplate会捕捉TransactionCallback中抛出的unchecked exception并回滚事务,然后将exception抛出给上层处理,
     * 所以现在,我们只需要在处理特定于应用程序充的异常即可.
     * 自动提交事务
     * 不自动提交:抛出异常和设置rollbackonly标记
     */
    public void transactionTemplateTest() {
        TransactionTemplate transactionTemplate = new TransactionTemplate();
        Object result = transactionTemplate.execute(new TransactionCallback() {
            public Object doInTransaction(TransactionStatus transactionStatus) {
                // 各种事物操作
                return null;
            }
        });
        //或者
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                //事务操作1
                //事务操作2
            }
        });
    }

    /**
     * 编程创建基于savepoint的嵌套事务
     * 使用嵌套事务的好处是,在事务2的操作失败,能够将这些数据回滚.而不会破坏当前事务的完整性
     */
    public void transactionTemplateSavepointTest() {
        TransactionTemplate transactionTemplate = new TransactionTemplate();
        Object result = transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                BigDecimal bigDecimal = new BigDecimal("20000");
                try {
                    //事务操作1
                    Object savePointBefore2 = transactionStatus.createSavepoint();
                    try {
                        //事务操作2
                    } catch (Exception e) {
                        transactionStatus.rollbackToSavepoint(savePointBefore2);
                    } finally {
                        transactionStatus.releaseSavepoint(savePointBefore2);
                    }
                } catch (Exception e) {
                    transactionStatus.setRollbackOnly();
                }
            }
        });
    }
}
