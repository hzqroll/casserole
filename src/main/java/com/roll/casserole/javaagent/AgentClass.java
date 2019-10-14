package com.roll.casserole.javaagent;

import java.lang.reflect.Method;

/**
 * @author roll
 * created on 2019-10-05 19:57
 */
public class AgentClass {
    public static void main(String[] args) {
        Class<?> cls = null;
        try {
            cls = Class.forName("com.roll.casserole.javaagent.TransformTest1");
            Method method = cls.getMethod("getInstance");
            Method getMethod = cls.getMethod("print");
            method.setAccessible(Boolean.TRUE);
            getMethod.setAccessible(Boolean.TRUE);
            TransformTest1 transformTest1 = new TransformTest1();
            getMethod.invoke(transformTest1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
