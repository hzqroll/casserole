package com.roll.casserole.spring.lifecycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;

/**
 * @author roll
 * created on 2020/6/7 4:09 下午
 */
public class AnnotatedBeanDefinitionParsingDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 基于Java 注解 AnnotatedBeanDefinitionReader 的实现
        AnnotatedBeanDefinitionReader beanDefinitionReader = new AnnotatedBeanDefinitionReader(beanFactory);
        int beanDefinitionCountBefore = beanFactory.getBeanDefinitionCount();
        // 注册当前类（非 @Component class）
        beanDefinitionReader.register(AnnotatedBeanDefinitionParsingDemo.class);
        int beanDefinitionCountAfter = beanFactory.getBeanDefinitionCount();
        int beanDefinitionCount = beanDefinitionCountBefore - beanDefinitionCountAfter;
        System.out.println("已加载 BeanDefinition 数量：" + beanDefinitionCount);
        // 普通class 座位 Component 注册到 Spring IoC 容器后，通畅 Bean 名称为 annotatedBeanDefinitionParsingDemo
        // Bean 名称生成来自于 BeanNameGenerator， 注解实现 AnnotatedBeanNameGenerator
        AnnotatedBeanDefinitionParsingDemo demo = beanFactory.getBean("annotatedBeanDefinitionParsingDemo", AnnotatedBeanDefinitionParsingDemo.class);
        System.out.println(demo);

    }
}
