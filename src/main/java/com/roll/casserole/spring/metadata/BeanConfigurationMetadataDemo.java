package com.roll.casserole.spring.metadata;

import com.roll.casserole.spring.common.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.util.ObjectUtils;

/**
 * Bean 配置元信息示例
 *
 * @author roll
 * created on 2020/6/12 8:53 上午
 */
public class BeanConfigurationMetadataDemo {
    public static void main(String[] args) {
        // BeanDefinition 的定义（声明）

        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("name", "roll1");
        // 获取 AbstractBeanDefinition
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        // 附加属性(不影响 Bean populate，initialize)
        beanDefinition.setAttribute("name", "roll");
        //
        beanDefinition.setSource(BeanConfigurationMetadataDemo.class);

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (ObjectUtils.nullSafeEquals("user", beanName) && User.class.equals(bean.getClass())) {
                    BeanDefinition bd = beanFactory.getBeanDefinition(beanName);
                    // 通过source 判断
                    if (BeanConfigurationMetadataDemo.class.equals(bd.getSource())) {
                        // 属性存储上下文
                        String name = (String) bd.getAttribute("name");
                        User user = (User) bean;
                        user.setName(name);
                        System.out.println(name);
                    }
                }
                return bean;
            }
        });

        beanFactory.registerBeanDefinition("user", beanDefinitionBuilder.getBeanDefinition());

        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);
    }
}
