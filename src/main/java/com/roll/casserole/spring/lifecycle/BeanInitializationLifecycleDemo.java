package com.roll.casserole.spring.lifecycle;

import com.roll.casserole.spring.common.User;
import com.roll.casserole.spring.common.UserHolder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * @author roll
 * created on 2020/6/10 8:57 上午
 */
public class BeanInitializationLifecycleDemo {
    public static void main(String[] args) {
        executeBeanFactory();
    }

    private static void executeBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 添加 BeanPostProcessor 实现
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        //  基于 XML 资源的 BeanDefinition
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String location = "META-INF/dependency-lookup-context.xml";
        // 基于ClassPath 加载 properties
        Resource resource = new ClassPathResource(location);
        // 指定字符编码 UTF-8
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
        beanDefinitionReader.loadBeanDefinitions(encodedResource);
        int beanDefinitionCountNumber = beanFactory.getBeanDefinitionCount();
        System.out.println("已加载 BeanDefinition 数量：" + beanDefinitionCountNumber);

        // 通过 BeanId 进行查找
        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);

        User superUser = beanFactory.getBean("superUser", User.class);
        System.out.println(superUser);

        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
        System.out.println(userHolder);
    }
}
