package com.roll.casserole.annotation;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.SOURCE)
@Documented
@Target(ElementType.TYPE)
public @interface TestAnnotation {
    public int id();

    public String msg() default "msg";
}
