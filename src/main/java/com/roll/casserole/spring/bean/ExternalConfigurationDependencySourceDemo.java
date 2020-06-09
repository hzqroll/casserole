package com.roll.casserole.spring.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

/**
 * @author roll
 * created on 2020/6/9 8:54 上午
 */
@Configuration
@PropertySource("classpath:META-INF/default.properties")
public class ExternalConfigurationDependencySourceDemo {

    @Value("${user.id:-1}")
    private Long id;

    @Value("${user.name}")
    private String name;

    @Value("${user.source}")
    private Resource resource;

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(ExternalConfigurationDependencySourceDemo.class);
        applicationContext.refresh();

        ExternalConfigurationDependencySourceDemo dependencySourceDemo = applicationContext.getBean(ExternalConfigurationDependencySourceDemo.class);

        System.out.println("demo.id=" + dependencySourceDemo.id);
        // 相同名称，外部化配置优先级较低
        System.out.println("demo.name=" + dependencySourceDemo.name);
        System.out.println("demo.resource=" + dependencySourceDemo.resource);

        applicationContext.close();
    }
}
