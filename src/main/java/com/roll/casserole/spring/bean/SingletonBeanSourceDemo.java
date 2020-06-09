package com.roll.casserole.spring.bean;

import com.roll.casserole.spring.common.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 单例对象demo
 *
 * @author roll
 * created on 2020/6/9 9:46 上午
 */
public class SingletonBeanSourceDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        User user = new User();
        user.setId(1L);
        user.setName("singletonName");
        applicationContext.getDefaultListableBeanFactory().registerSingleton("singletonUser", user);
        applicationContext.refresh();
        User user1 = (User) applicationContext.getDefaultListableBeanFactory().getSingleton("singletonUser");
        System.out.println(user1);
        applicationContext.close();
    }
}
