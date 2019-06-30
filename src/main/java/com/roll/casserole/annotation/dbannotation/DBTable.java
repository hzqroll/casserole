package com.roll.casserole.annotation.dbannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 次注解用于高速注解处理器，你需要为我生成一个数据库表
 *
 * @author haozq
 * Date: 2018/8/19 下午2:12
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DBTable {
	public String name() default "";
}
