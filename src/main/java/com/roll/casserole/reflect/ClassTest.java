package com.roll.casserole.reflect;

import com.roll.casserole.controller.DubboController;
import com.roll.casserole.design.proxy.Pomeranian;
import com.roll.casserole.event.MethodExecutionEvent;
import com.roll.casserole.event.MethodExecutionEventPublisher;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author zongqiang.hao
 * created on 2019-02-22 09:25.
 */
public class ClassTest {
    public static void main(String[] args) {
        try {

            System.out.println(Arrays.toString(ClassEnum.class.getEnumConstants()));
            // System.out.println((ClassEnum.class.()));

            System.out.println(Arrays.toString(Pomeranian.class.getAnnotations()));


            Package pa = Pomeranian.class.getPackage();

            Class aClass = Pomeranian.class.getProtectionDomain().getCodeSource().getLocation().getClass();
            System.out.println(aClass.getName());

            System.out.println((Arrays.toString(Pomeranian.class.getMethods())));

            System.out.println(Arrays.toString(Pomeranian.class.getInterfaces()));
            System.out.println(Arrays.toString(Pomeranian.class.getGenericInterfaces()));

            Field field = Pomeranian.class.getField("time");
            System.out.println(field.getName());
            System.out.println(field.getType());
            System.out.println(Arrays.toString(Pomeranian.class.getDeclaredFields()));

            Constructor constructor = Pomeranian.class.getConstructor(int.class);
            Pomeranian pomeranian = (Pomeranian) constructor.newInstance(100);
            pomeranian.print();

            Method method = Pomeranian.class.getMethod("print");
            method.invoke(pomeranian);

            System.out.println(Pomeranian.class.toGenericString());

        } catch (NoSuchFieldException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
