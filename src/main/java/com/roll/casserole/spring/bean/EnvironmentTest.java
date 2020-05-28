package com.roll.casserole.spring.bean;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

import java.util.List;
import java.util.Map;

/**
 * @author roll
 * created on 2020/5/28 3:22 下午
 */
@PropertySource("classpath:application.properties")
@Configuration
@ConfigurationProperties(prefix = "test")
@SpringBootApplication
public class EnvironmentTest implements EnvironmentAware {
    @Value(value = "${test.value}")
    private String value;

    private String a;
    private String b;

    @Autowired
    private Environment environment1;

    @Override
    public void setEnvironment(Environment environment) {
        //value = environment.getProperty("test.value");
        //System.out.println(environment.getRequiredProperty("test.value"));
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        System.out.println(environment1.getRequiredProperty("test.value"));
        return value;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.register(EnvironmentTest.class);
        annotationConfigApplicationContext.refresh();
        ConfigurableEnvironment environment = annotationConfigApplicationContext.getEnvironment();
        System.out.println(environment.getProperty("test.value"));

        List<Map<String, String>> map = environment.getProperty("map.value", List.class);
        System.out.println(map);

        EnvironmentTest environmentTest = annotationConfigApplicationContext.getBean("environmentTest", EnvironmentTest.class);
        System.out.println(environmentTest.getA());
        System.out.println(environmentTest.getB());
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }
}
