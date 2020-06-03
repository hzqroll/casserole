package com.roll.casserole.spring.bean;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ResolvableType;

import java.util.Arrays;

/**
 * @author roll
 * created on 2020/5/30 2:59 下午
 */
@Configuration
public class ApplicationEventPublisherTest implements ApplicationEventPublisherAware, ApplicationListener<SomeEvent> {

    private String a;
    public static void main(String[] args) {
        // 1 XML注册
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ApplicationEventPublisherTest.class);
        // 启动应用上下文
        applicationContext.refresh();

        //   启动后，事件注册，并完成监听
        // 关闭应用上下文
        applicationContext.close();

        // ResolvableType 测试
        ResolvableType resolvableType = ResolvableType.forClass(ApplicationEventPublisherTest.class);
        System.out.println(resolvableType.getRawClass());
        System.out.println(resolvableType.getGeneric(0));
        System.out.println(resolvableType.getGeneric(1));
        System.out.println(Arrays.toString(resolvableType.getInterfaces()));
        System.out.println(resolvableType.getComponentType());


    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        System.out.println("注册事件");
        SomeEvent someEvent = new SomeEvent("事件Event");
        applicationEventPublisher.publishEvent(someEvent);
    }

    @Override
    public void onApplicationEvent(SomeEvent event) {
        System.out.println("监听到事件发布：" + event.getSource());
    }
}

class SomeEvent extends ApplicationEvent {
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public SomeEvent(Object source) {
        super(source);
    }
}
