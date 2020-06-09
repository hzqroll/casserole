package com.roll.casserole.spring.bean;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author roll
 * created on 2020/6/9 8:44 上午
 */
public class ResolvableDependencySourceDemo {
    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(ResolvableDependencySourceDemo.class);
        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            // 注册 Resolvable Dependency
            beanFactory.registerResolvableDependency(String.class, "hello world");
        });

        applicationContext.refresh();

        //AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();
//        if (beanFactory instanceof ConfigurableListableBeanFactory) {
//            ConfigurableListableBeanFactory configurableListableBeanFactory = ConfigurableListableBeanFactory.class.cast(beanFactory);
//        }

        applicationContext.close();
    }

    @Autowired
    private String value;

    @PostConstruct
    public void init() {
        System.out.println(value);
    }
}
