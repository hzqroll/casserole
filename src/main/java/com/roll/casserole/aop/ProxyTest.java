package com.roll.casserole.aop;

import com.roll.casserole.aop.aspect.AspectTest;
import com.roll.casserole.common.DogTalk;
import com.roll.casserole.common.Talk;
import com.roll.casserole.common.User;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zongqiang.hao
 * created on 2019-03-11 19:36.
 */
public class ProxyTest {
    private static Talk talk = new DogTalk();

    public static void main(String[] args) {
        //proxyFactoryTest();
        //aspectSimpleTest();
        //aspectSimpleAutoTest();
        //aspectSinpleArgsTest();
        aspectBeforeArgsTest();
    }

    /**
     * 名称匹配简单例子
     */
    private static void proxyFactoryTest() {
        ProxyFactory weave = new ProxyFactory();
        weave.setTarget(talk);

        Advisor advisor = new NameMatchMethodPointcutAdvisor();
        ((NameMatchMethodPointcutAdvisor) advisor).addMethodName("say");
        weave.addAdvisor(advisor);

        Object proxyFactory = weave.getProxy();
        Talk talk = (Talk) proxyFactory;
        talk.say();
    }

    /**
     * aspect简单🌰
     * 编程方式织入
     */
    private static void aspectSimpleTest() {
        AspectJProxyFactory weaver = new AspectJProxyFactory();
        weaver.setProxyTargetClass(true);
        weaver.setTarget(new DogTalk());
        weaver.addAspect(AspectTest.class);
        Object proxy = weaver.getProxy();
        ((DogTalk) proxy).say();
    }

    private static void aspectSimpleAutoTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:aop.xml");
        DogTalk dogTalk = (DogTalk) context.getBean("talk");
        dogTalk.say();
    }

    private static void aspectSinpleArgsTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:aop.xml");
        DogTalk dogTalk = (DogTalk) context.getBean("talk");
        User user = new User("1", 2);
        dogTalk.initUser(user);
    }

    private static void aspectBeforeArgsTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:aop.xml");
        DogTalk dogTalk = (DogTalk) context.getBean("talk");
        dogTalk.say("h1");
    }
}
