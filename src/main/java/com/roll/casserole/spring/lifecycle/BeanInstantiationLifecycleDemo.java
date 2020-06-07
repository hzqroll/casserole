package com.roll.casserole.spring.lifecycle;

import com.roll.casserole.spring.common.SuperUser;
import com.roll.casserole.spring.common.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.ObjectUtils;

/**
 * @author roll
 * created on 2020/6/7 6:08 下午
 */
public class BeanInstantiationLifecycleDemo {
    public static void main(String[] args) {
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
    }

    static class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
        @Override
        public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
            if (ObjectUtils.nullSafeEquals("superUser", beanName) && SuperUser.class.equals(beanClass)) {
                // 把配置好的 superUser Bean 覆盖
                return new SuperUser();
            }
            // 保持 Spring IoC容器实例化
            return null;
        }
    }
}
