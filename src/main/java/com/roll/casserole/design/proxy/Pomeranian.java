package com.roll.casserole.design.proxy;

import javax.xml.ws.ServiceMode;

/**
 * 博美
 *
 * @author zongqiang.hao
 * created on 2019-02-21 11:10.
 */
@ServiceMode
public class Pomeranian implements Dog {
    public int time = 1;

    public Pomeranian() {
    }

    public Pomeranian(int time) {
        this.time = time;
    }

    public void print() {
        System.out.println("time: " + time);
    }

    @Override
    public void run() {
        System.out.println("博美跑起来了.");
    }

    public class Test {

    }
}
