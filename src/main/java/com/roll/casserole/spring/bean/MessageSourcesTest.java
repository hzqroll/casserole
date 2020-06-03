package com.roll.casserole.spring.bean;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Locale;

/**
 * @author roll
 * created on 2020/5/30 11:21 上午
 */
public class MessageSourcesTest {
    public static void main(String[] args) {
        // 1 XML注册
        AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:common.xml");
        // 启动应用上下文
        applicationContext.refresh();
        // 制定指定Message消息
        System.out.println(applicationContext.getMessage("hello", null, "default", Locale.JAPANESE));
        System.out.println(applicationContext.getMessage("hello", null, "default", Locale.CHINESE));
        System.out.println(applicationContext.getMessage("hello", null, "default", Locale.ENGLISH));

        // 关闭应用上下文
        applicationContext.close();
    }
}
