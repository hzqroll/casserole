package com.roll.casserole.spring.lifecycle;

import com.roll.casserole.spring.common.SuperUser;
import com.roll.casserole.spring.common.User;
import com.roll.casserole.spring.common.UserHolder;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.util.ObjectUtils;

/**
 * @author roll
 * created on 2020/6/10 8:57 上午
 */
class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("superUser", beanName) && SuperUser.class.equals(beanClass)) {
            // 把配置好的 superUser Bean 覆盖
            return new SuperUser();
        }
        // 保持 Spring IoC容器实例化
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("user", beanName) && User.class.equals(bean.getClass())) {
            // user 对象不允许属性赋值（配置元信息 -> 属性值）
            return false;
        }
        return true;
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("userHolder", beanName) && UserHolder.class.equals(bean.getClass())) {
            // user 对象不允许属性赋值（配置元信息 -> 属性值）
            MutablePropertyValues propertyValues;
            if (pvs instanceof MutablePropertyValues) {
                propertyValues = (MutablePropertyValues) pvs;
            } else {
                propertyValues = new MutablePropertyValues();
            }
            propertyValues.addPropertyValue("number", "123");

            // 如果存在 desc 属性配置的话
            if (propertyValues.contains("desc")) {
                propertyValues.removePropertyValue("desc");
                propertyValues.addPropertyValue("desc", "the user holder v2");
            }
            return propertyValues;
        }
        return null;
    }

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("userHolder", beanName) && UserHolder.class.equals(bean.getClass())) {
            UserHolder userHolder = (UserHolder) bean;
            // userHolder desc = the user holder v2
            userHolder.setDesc("the user holder v3");
        }
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("userHolder", beanName) && UserHolder.class.equals(bean.getClass())) {
            UserHolder userHolder = (UserHolder) bean;
            // userHolder desc = the user holder v2
            userHolder.setDesc("the user holder v7");
        }
        return bean;
    }
}
