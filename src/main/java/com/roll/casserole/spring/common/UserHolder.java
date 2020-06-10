package com.roll.casserole.spring.common;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;

/**
 * @author roll
 * created on 2020/6/9 9:44 下午
 */
public class UserHolder implements BeanNameAware, BeanFactoryAware, BeanClassLoaderAware {

    private User user;

    private Integer number;

    private String desc;

    public UserHolder(User user) {
        this.user = user;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                ", number=" + number +
                ", desc='" + desc + '\'' +
                '}';
    }

    private BeanFactory beanFactory;

    private ClassLoader classLoader;

    private String beanName;

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }
}
