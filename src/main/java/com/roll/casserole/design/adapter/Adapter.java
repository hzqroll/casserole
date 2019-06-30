package com.roll.casserole.design.adapter;

/**
 * @author zongqiang.hao
 * created on 2019-06-28 16:39.
 */
public class Adapter implements Target {
    private Adaptee adaptee;

    @Override
    public void request() {
        adaptee.specialMethod();
    }

    public static void main(String[] args) {
        Target target = new Adapter();
        target.request();
    }
}

