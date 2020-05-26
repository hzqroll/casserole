package com.roll.casserole.design.observer.java;

/**
 * @author zongqiang.hao
 * created on 2020/5/8 9:06 下午.
 */
public class Teacher {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                '}';
    }
}
