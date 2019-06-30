package com.roll.casserole.proxy;

/**
 * @author zongqiang.hao
 * created on 2019-02-27 20:19.
 */
public class SliverIron implements Iron {
    @Override
    public void print(String name) {
        System.out.println("I am a sliver. " + name);
    }
}
