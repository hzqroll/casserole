package com.roll.casserole.spring.common;

/**
 * @author roll
 * created on 2020/5/29 5:59 下午
 */
public class Rose implements Plant {
    @Override
    public String getColor() {
        return "red";
    }

    @Override
    public int getAge() {
        return 1;
    }
}
