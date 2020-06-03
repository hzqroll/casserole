package com.roll.casserole.spring.bean;

import com.roll.casserole.common.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

/**
 * @author roll
 * created on 2020/5/30 9:51 上午
 */
@Configuration
public class HierarchicalBeanFactoryTest {
    public static void main(String[] args) {
        // 1 XML注册
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.setParent(getApplicationContext());
        applicationContext.register(HierarchicalBeanFactoryTest.class);
        // 启动应用上下文
        applicationContext.refresh();

        // 制定当前bean的父类
        System.out.println(applicationContext.getParentBeanFactory());
        //  获取父类的bean
        System.out.println(applicationContext.getBean("user-listable"));
        // 本地factory是否包含某个bean
        System.out.println(applicationContext.containsLocalBean("user-listable"));
        // 本地factory是否包含某个bean
        System.out.println(applicationContext.containsLocalBean("getUserBean1"));
        // 关闭应用上下文
        applicationContext.close();
    }

    public static ApplicationContext getApplicationContext() {
        // 1 XML注册
        return new ClassPathXmlApplicationContext("classpath:lookup-bean.xml");
    }

    @Bean
    public User getUserBean1() {
        return new User();
    }
}
