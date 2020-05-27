package com.roll.casserole.spring.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Profiles;

/**
 * @author roll
 * created on 2020/5/26 2:15 下午
 */
public class XMLBeanTest {
    public static void main(String[] args) {
        // 1 XML注册
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:bean.xml");
        applicationContext.getEnvironment().acceptsProfiles(Profiles.of("dev"));
        System.out.println(applicationContext.getBean("user"));
    }
}
