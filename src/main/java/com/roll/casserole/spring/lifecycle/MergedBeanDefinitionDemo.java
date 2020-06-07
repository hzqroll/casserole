package com.roll.casserole.spring.lifecycle;

import com.roll.casserole.spring.common.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * BeanDefinition 合并示例
 *
 * @author roll
 * created on 2020/6/7 5:02 下午
 */
public class MergedBeanDefinitionDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        //  基于XML资源的 BeanDefinition
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String location = "META-INF/dependency-lookup-context.xml";
        // 基于ClassPath 加载 propreties
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
    }
}

