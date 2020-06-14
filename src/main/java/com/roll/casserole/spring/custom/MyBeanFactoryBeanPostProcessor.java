package com.roll.casserole.spring.custom;

import com.roll.casserole.spring.common.SubUser;
import com.roll.casserole.spring.common.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author roll
 * created on 2020/6/14 12:54 下午
 */
@Configuration
public class MyBeanFactoryBeanPostProcessor implements BeanFactoryPostProcessor {


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        try {
            User user = beanFactory.getBean(User.class);
            user.setName("postProcessBeanFactoryName");

            // 注册 resolvableDependency Bean
            SubUser subUser = new SubUser();
            subUser.setName(user.getName());
            subUser.setSubName("postProcessBeanFactorySubName");
            beanFactory.registerResolvableDependency(SubUser.class, subUser);
        } catch (NoSuchBeanDefinitionException e) {
            e.printStackTrace();
        }
    }

    @Bean
    public User user() {
        User user = new User();
        user.setName("commonName");
        return user;
    }
}
