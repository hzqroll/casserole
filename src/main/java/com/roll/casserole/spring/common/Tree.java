package com.roll.casserole.spring.common;

/**
 * @author roll
 * created on 2020/5/29 6:01 下午
 */
public class Tree implements Plant {
    @Override
    public String getColor() {
        return "green";
    }

    @Override
    public int getAge() {
        return 16;
    }
}
