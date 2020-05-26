package com.roll.think.in.spring.ioc.overview.container;

import com.roll.think.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * IoC容器实例
 *
 * @author zongqiang.hao
 * created on 2020/5/13 9:39 下午.
 */
public class BeanFactoryAsIoCContainerDemo {
    public static void main(String[] args) {
        // 创建BeanFactory 容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 加载配置
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        // xml配置文件的classpath路径
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        // 记载资源
        int beanDefinitionsCount = reader.loadBeanDefinitions(location);
        System.out.println("bean 定义的加载数量：" + beanDefinitionsCount);

        // 依赖查找集合对象
        lookupByCollectionType(beanFactory);
    }

    public static void lookupByCollectionType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> userMap = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找所有的结合对象" + userMap);
        }
    }
}
