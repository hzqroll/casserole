package com.roll.casserole.reflect;

/**
 * @author zongqiang.hao
 * created on 2019-02-26 10:37.
 */
public class BigCat extends Cat {
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "BigCat{" +
                "age=" + age +
                "} " + super.toString();
    }
}
