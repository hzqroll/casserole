package com.roll.casserole.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 一个注解，如果注解了某个用例的需求，其他方法用到了该注解，就可以计算那些地方用到了对应的用例
 *
 * @author haozq
 * Date: 2018/8/19 下午12:04
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UserCase {
	public int id();

	public String description() default "default description";

}
