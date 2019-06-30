package com.roll.casserole.annotation.dbannotation;

/**
 * 如果要令嵌入的@Constraints注解中的unique（）元素为truw，并以此作为
 * Constraints元素的默认值，组需要定义下面元素
 *
 * @author haozq
 * Date: 2018/8/19 下午2:21
 */
public @interface Uniqueness {
	Constraints constraints()
			default @Constraints(unique = true);
}
