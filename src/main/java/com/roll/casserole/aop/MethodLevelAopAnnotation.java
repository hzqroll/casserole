package com.roll.casserole.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用户方法级别注解AOP
 *
 * @author zongqiang.hao
 * created on 2019-03-09 21:37.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MethodLevelAopAnnotation {
}
