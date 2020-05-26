package com.roll.think.in.spring.ioc.overview.container;

import com.roll.think.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * IoC容器实例
 *
 * @author zongqiang.hao
 * created on 2020/5/13 9:39 下午.
 */
@Configuration
public class AnnotationApplicationContextIoCContainerDemo {
    public static void main(String[] args) {
        // 创建BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //将当前累 AnnotationApplicationContextIoCContainerDemo  作为配置类（Configuration Class）
        applicationContext.register(AnnotationApplicationContextIoCContainerDemo.class);
        // 启动应用上下文
        applicationContext.refresh();

        // 依赖查找集合对象
        lookupByCollectionType(applicationContext);
    }

    @Bean
    public User user() {
        User user = new User();
        user.setId(2L);
        user.setName("哈哈");
        return user;
    }

    public static void lookupByCollectionType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> userMap = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找所有的结合对象" + userMap);
        }
    }
}
