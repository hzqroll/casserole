package com.roll.casserole.spring.bean;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.env.EnvironmentCapable;

/**
 * @author roll
 * created on 2020/5/26 2:15 下午
 */
public class XMLBeanTest {
    public static void main(String[] args) {
        // 1 XML注册
        AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.getEnvironment().setActiveProfiles("dev");
        applicationContext.refresh();
        System.out.println(applicationContext.getBean("user"));

        //EnvironmentTest environmentTest = applicationContext.getBean("environmentTest", EnvironmentTest.class);
        //System.out.println(environmentTest.getValue());
        Environment environment = applicationContext.getEnvironment();
        System.out.println(environment.getProperty("test.value"));
    }
}
