package com.roll.casserole.spring.bean;

import com.roll.casserole.common.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author roll
 * created on 2020/5/26 3:54 下午
 */
public class ConfigurationBeanTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.refresh();
        User user = annotationConfigApplicationContext.getBean(User.class);
        System.out.println(user);
    }
}

@Configuration
class ConfigurationTest {
    @Bean
    public User getUser() {
        return new User("莱昂", 17);
    }
}
