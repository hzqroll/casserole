package com.roll.casserole.spring.common;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author roll
 * created on 2020/6/9 9:44 下午
 */
public class UserHolder implements BeanNameAware, BeanFactoryAware, BeanClassLoaderAware, InitializingBean, SmartInitializingSingleton, DisposableBean {

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

    /**
     * 依赖于注解驱动
     * 当前场景：BeanFactory
     */
    @PostConstruct
    public void initPostConstruct() {
        this.desc = "the user holder v4";
        System.out.println("PostConstruct() = " + desc);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.desc = "the user holder v5";
        System.out.println("afterPropertiesSet() = " + desc);
    }

    /**
     * 自定义初始化方法
     */
    public void init() {
        this.desc = "the user holder v6";
        System.out.println("init() = " + desc);
    }

    @Override
    public void afterSingletonsInstantiated() {
        this.desc = "the user holder v8";
        System.out.println("afterSingletonsInstantiated() = " + desc);
    }

    @PreDestroy
    public void preDestroy() {
        this.desc = "the user holder v10";
        System.out.println("preDestroy() = " + desc);
    }

    @Override
    public void destroy() throws Exception {
        this.desc = "the user holder v11";
        System.out.println("destroy() = " + desc);
    }

    public void doDestroy() {
        this.desc = "the user holder v12";
        System.out.println("doDestroy() = " + desc);
    }

    protected void finalize() throws Throwable {
        System.out.println("The userHolder is finalized...");
    }
}
