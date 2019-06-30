package com.roll.casserole.reflect;

import java.lang.reflect.Field;

/**
 * @author zongqiang.hao
 * created on 2019-02-26 10:38.
 */
public class ReflectTest {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Cat cat = new Cat();
        cat.setName("123");

        //Field field = cat.getClass().getDeclaredField("name");
        //field.setAccessible(true);
        //field.set(cat, "456");

        Field field = cat.getClass().getDeclaredField("name");
        field.set(cat, "haha");
        System.out.println(cat);
    }

    private static void getCat(Cat cat) {
        cat = new BigCat();
        cat.setName("");
        ((BigCat) cat).setAge(123);

        System.out.println(cat);
    }
}
