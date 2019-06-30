package com.roll.casserole.aop;

import com.roll.casserole.aop.Advice.IntroductionTest;
import com.roll.casserole.aop.dynamic.DynamicProxy;
import com.roll.casserole.common.DogTalk;
import com.roll.casserole.common.SimpleAdvice;
import com.roll.casserole.common.Talk;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Method;

/**
 * @author zongqiang.hao
 * created on 2019-03-07 20:22.
 */
public class NameMatcherPointcutTest {

    public static void main(String[] args) {
        //springAopTest();
        //annotationAopTest();
        //dynamicTest();
        //System.out.println("123");
        //dynamicTest2();
        autoProxyTest();
    }

    private static void springAopTest() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml", "classpath:aop.xml");

        Talk talk = (Talk) applicationContext.getBean("proxyFactoryBean");
        talk.say();
        talk.say("12haha");
    }

    /**
     * java代码advisor
     */
    private static void javaAopTest() {
        Talk talk = new DogTalk();

        NameMatchMethodPointcut nameMatchMethodPointcut = new NameMatchMethodPointcut();
        nameMatchMethodPointcut.addMethodName("say");

        Advisor advisor = new DefaultPointcutAdvisor(nameMatchMethodPointcut, new SimpleAdvice());

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(talk);
        proxyFactory.addAdvisor(advisor);

        Talk proxy = (Talk) proxyFactory.getProxy();
        proxy.say();
        proxy.say("haha");
    }

    /**
     * 注解proxy
     */
    private static void annotationAopTest() {
        Talk talk = new DogTalk();

        AnnotationMatchingPointcut nameMatchMethodPointcut = new AnnotationMatchingPointcut(MethodLevelAopAnnotation.class);

        Advisor advisor = new DefaultPointcutAdvisor(nameMatchMethodPointcut, new SimpleAdvice());

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(talk);
        proxyFactory.addAdvisor(advisor);

        Talk proxy = (Talk) proxyFactory.getProxy();
        proxy.say();
        proxy.say("haha");
    }

    /**
     * 配置文件proxy
     */
    private static void intorductionTest() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml", "classpath:aop.xml");
        Talk talk = (Talk) applicationContext.getBean("proxyFactoryBeanIntroduction");
        talk.say();
        talk.say("12haha");
        ((IntroductionTest) talk).additionFun();
    }

    /**
     * 测试动态代理
     */
    private static void dynamicTest() {
        Talk talk = new DogTalk();
        DynamicProxy dynamicProxy = new DynamicProxy(talk);
        try {
            Method method = talk.getClass().getMethod("getName", String.class);
            Object[] objects = new Object[1];
            objects[0] = "打印";
            dynamicProxy.invoke(null, method, objects);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试动态代理, 内置getProxyInstance
     */
    private static void dynamicTest2() {
        Talk talk = new DogTalk();
        DynamicProxy dynamicProxy = new DynamicProxy(talk);

        Talk proxy = (Talk) dynamicProxy.getProxy(dynamicProxy);
        proxy.say("3242");
    }

    /**
     * 测试MethodBeforeAdvice
     */
    private static void beforeTalkTest() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        Talk talk = (Talk) applicationContext.getBean("beforeTalkAopTest");
        talk.say();
    }

    /**
     * 使用defaultAdvisorAutoProxyCreator
     * 只需要一个Advisor即可
     */
    private static void autoProxyTest() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        Talk talk = (Talk) applicationContext.getBean("talk");
        talk.say();
    }

}
