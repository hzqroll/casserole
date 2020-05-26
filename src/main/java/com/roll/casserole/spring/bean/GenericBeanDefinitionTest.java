package com.roll.casserole.spring.bean;

import com.roll.casserole.common.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * @author roll
 * created on 2020/5/26 3:16 下午
 */
public class GenericBeanDefinitionTest {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 构造bean
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(User.class);
        PropertyValue propertyValue = new PropertyValue("age", "11");
        PropertyValue propertyValue1 = new PropertyValue("name", "里斯");
        MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
        mutablePropertyValues.addPropertyValue(propertyValue);
        mutablePropertyValues.addPropertyValue(propertyValue1);
        genericBeanDefinition.setPropertyValues(mutablePropertyValues);

        // 注册bean
        beanFactory.registerBeanDefinition("genericUserBean", genericBeanDefinition);

        // 获取Bean
        User user = beanFactory.getBean("genericUserBean", User.class);
        System.out.println(user);
    }
}
