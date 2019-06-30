package com.roll.casserole.reflect;

/**
 * @author zongqiang.hao
 * created on 2019-02-27 09:14.
 */
public enum ClassEnum {
    A("1"),

    B("2");

    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    ClassEnum(String time) {
        this.time = time;
    }}
