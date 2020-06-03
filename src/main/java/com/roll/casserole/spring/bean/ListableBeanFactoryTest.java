package com.roll.casserole.spring.bean;

import com.roll.casserole.common.User;
import com.roll.casserole.spring.common.Plant;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.Map;

/**
 * @author roll
 * created on 2020/5/29 5:15 下午
 */
public class ListableBeanFactoryTest {
    public static void main(String[] args) {
        // 1 XML注册
        AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:common.xml");
        applicationContext.setParent(getApplicationContext());
        // 启动应用上下文
        applicationContext.refresh();

        // 制定名称获取bean
        System.out.println(applicationContext.getBean("user-common"));

        // 获取当前bean数量，不包含父类的bean
        System.out.println(applicationContext.getBeanDefinitionCount());

        // 根据类型获取bean名称数组
        System.out.println(Arrays.toString(applicationContext.getBeanNamesForType(User.class)));

        // 根据类型获取bean定义的map结构
        Map<String, Plant> plantMap = applicationContext.getBeansOfType(Plant.class);
        System.out.println(plantMap);

        // 关闭应用上下文
        applicationContext.close();
    }

    public static ApplicationContext getApplicationContext() {
        // 1 XML注册
        return new ClassPathXmlApplicationContext("classpath:lookup-bean.xml");
    }
}
