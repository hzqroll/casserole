package com.roll.casserole.spring.metadata;

import com.roll.casserole.spring.common.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;

/**
 * {@link org.springframework.beans.factory.support.PropertiesBeanDefinitionReader}
 *
 * @author roll
 * created on 2020/6/13 4:51 下午
 */
public class PropertiesBeanDefinitionReaderDemo {
    public static void main(String[] args) {
        // 创建IoC 底层容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 创建面向 properties 资源的 BeanDefinitionReader 的示例
        PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);
        // properties 资源加载默认通过 ISO-8859-1， 实际存储 UTF-8
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        // 通过指定的ClassPath 获取 Resource 对象
        Resource resource = resourceLoader.getResource("classpath:/META-INF/user-bean-definition.properties");
        // 转换成带有字符编码的 EncodedResource 对象
        EncodedResource encodedResource = new EncodedResource(resource, "utf-8");
        int beanDefinitionsCount = beanDefinitionReader.loadBeanDefinitions(encodedResource);
        System.out.println(String.format("已加载 %d 个 BeanDefinition\n", beanDefinitionsCount));
        // 通过依赖查找 user bean, 只能通过类型来获取
        User user = beanFactory.getBean(User.class);
        System.out.println(user);

    }
}
