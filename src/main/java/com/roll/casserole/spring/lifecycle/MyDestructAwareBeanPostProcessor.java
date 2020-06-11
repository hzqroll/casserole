package com.roll.casserole.spring.lifecycle;

import com.roll.casserole.spring.common.UserHolder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.util.ObjectUtils;

/**
 * {@link org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor} 实现
 *
 * @author roll
 * created on 2020/6/11 8:48 上午
 */
public class MyDestructAwareBeanPostProcessor implements DestructionAwareBeanPostProcessor {

    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("userHolder", beanName) && UserHolder.class.equals(bean.getClass())) {
            UserHolder userHolder = (UserHolder) bean;
            // userHolder desc = the user holder v9
            userHolder.setDesc("the user holder v9");
            System.out.println("postProcessBeforeDestruction(): " + userHolder.getDesc());
        }
    }
}
