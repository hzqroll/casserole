package com.roll.casserole.design.proxy;

/**
 * @author zongqiang.hao
 * created on 2019-02-21 11:18.
 */
public class PomeranianTime implements Dog {

    private Dog dog;

    public PomeranianTime(Dog dog) {
        this.dog = dog;
    }

    @Override
    public void run() {
        System.out.println("start time: " + System.currentTimeMillis());
        dog.run();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end time: " + System.currentTimeMillis());
    }
}
