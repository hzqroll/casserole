package com.roll.casserole.spring.lifecycle;

import com.roll.casserole.spring.common.SubUser;
import com.roll.casserole.spring.common.User;
import com.roll.casserole.spring.custom.MyBeanFactoryBeanPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * @author roll
 * created on 2020/6/14 12:53 下午
 */
@Configuration
public class BeanFactoryPostProcessorDemo {

    @Autowired
    public SubUser subUser;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(MyBeanFactoryBeanPostProcessor.class);
        applicationContext.register(BeanFactoryPostProcessorDemo.class);
        applicationContext.refresh();

        User user = applicationContext.getBean(User.class);
        System.out.println(user);

        BeanFactoryPostProcessorDemo beanFactoryPostProcessorDemo = applicationContext.getBean(BeanFactoryPostProcessorDemo.class);
        System.out.println(beanFactoryPostProcessorDemo.subUser);
    }

    public SubUser getSubUser() {
        return subUser;
    }

    public void setSubUser(SubUser subUser) {
        this.subUser = subUser;
    }
}

