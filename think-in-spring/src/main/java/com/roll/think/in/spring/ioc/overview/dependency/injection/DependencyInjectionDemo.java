package com.roll.think.in.spring.ioc.overview.dependency.injection;

import com.roll.think.in.spring.ioc.overview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * @author zongqiang.hao
 * created on 2020/5/12 9:14 下午.
 */
public class DependencyInjectionDemo {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");

        // 依赖来源1：自定义Bean
        UserRepository userRepository = beanFactory.getBean(UserRepository.class);
        System.out.println(userRepository.toString());

        // 依赖来源2：依赖注入（内建依赖）
        ObjectFactory objectFactory = userRepository.getObjectFactory();
        System.out.println(objectFactory.getObject());

        System.out.println(objectFactory.getObject() == beanFactory);

        //  容器内建bean
        Environment environment = beanFactory.getBean(Environment.class);
        System.out.println("获取 Environment 类型的Bean" + environment);
    }

    private static void who(UserRepository userRepository, BeanFactory beanFactory) {
        // ConfigurableApplicationContext <- ApplicationContext <- BeanFactory
        // ConfigurationApplicationContext#getBeanFactory()
        
        // 这个表达式为什么不成立
        System.out.println(userRepository.getBeanFactory() == beanFactory);
        // ApplicationContext 就是 BeanFactory
    }


}
