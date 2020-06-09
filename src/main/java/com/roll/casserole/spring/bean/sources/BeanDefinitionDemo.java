package com.roll.casserole.spring.bean.sources;

import com.roll.casserole.spring.common.SuperUser;
import com.roll.casserole.spring.common.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author roll
 * created on 2020/6/8 8:16 上午
 */
public class BeanDefinitionDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 构造 user bean Definition
        BeanDefinitionBuilder userBeanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        userBeanDefinitionBuilder.addPropertyValue("id", 10);
        userBeanDefinitionBuilder.addPropertyValue("name", "beanDefinitionName");
        // 注册到 applicationContext
        applicationContext.registerBeanDefinition("user", userBeanDefinitionBuilder.getBeanDefinition());

        // 构造 user bean Definition
        BeanDefinitionBuilder superUserBeanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(SuperUser.class);
        superUserBeanDefinitionBuilder.addPropertyValue("parentName", "user");
        superUserBeanDefinitionBuilder.setParentName("user");
        // 注册到 applicationContext
        applicationContext.registerBeanDefinition("superUser", superUserBeanDefinitionBuilder.getBeanDefinition());

        // 刷新 applicationContext
        applicationContext.refresh();

        System.out.println(applicationContext.getBean("user"));
        System.out.println(applicationContext.getBean("superUser"));

        applicationContext.close();
    }
}
