package com.roll.casserole.spring.lifecycle;

import com.roll.casserole.spring.common.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * @author roll
 * created on 2020/6/7 2:59 下午
 */
public class BeanMetadataConfigurationDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 实例化 Properties 资源BeanDefinitionReader
        PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);
        String location = "META-INF/user.properties";
        // 基于ClassPath 加载 propreties
        Resource resource = new ClassPathResource(location);
        // 指定字符编码 UTF-8
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
        int beanNumbers = beanDefinitionReader.loadBeanDefinitions(encodedResource);
        System.out.println("已加载的 BeanDefinition 数量：" + beanNumbers);
        // 通过Bean Id 和类型进行依赖查找
        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);
    }
}
