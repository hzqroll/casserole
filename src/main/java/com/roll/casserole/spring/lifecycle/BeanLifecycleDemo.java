package com.roll.casserole.spring.lifecycle;

import com.roll.casserole.spring.common.UserHolder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * @author roll
 * created on 2020/6/11 8:52 上午
 */
public class BeanLifecycleDemo {
    public static void main(String[] args) throws InterruptedException {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 添加 BeanPostProcessor 实现
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        // 添加 CommonAnnotationBeanPostProcessor 回调
        beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());
        // 添加 MyDestruction，执行销毁前回调
        beanFactory.addBeanPostProcessor(new MyDestructAwareBeanPostProcessor());
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

        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
        //  显式执行 preInstantiateSingletons
        //  SmartInitializingSingleton 通畅在 Spring ApplicationContext 场景使用
        beanFactory.preInstantiateSingletons();
        System.out.println(userHolder);

        // 指定Bean销毁 (容器中)
        beanFactory.destroyBean("userHolder", userHolder);
        // Bean 销毁并不意味着 Bean 被 GC
        System.out.println(userHolder);

        // 销毁Bean Factory 的单例方法
        beanFactory.destroySingletons();
        System.gc();
        // 等待一段时间
        Thread.sleep(1000L);
        System.gc();
    }
}
