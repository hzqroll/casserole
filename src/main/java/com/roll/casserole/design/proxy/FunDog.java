package com.roll.casserole.design.proxy;

/**
 * @author zongqiang.hao
 * created on 2019-02-21 11:21.
 */
public class FunDog implements Dog {
    private Dog dog;

    public FunDog(Dog dog) {
        this.dog = dog;
    }

    @Override
    public void run() {
        dog.run();
        System.out.println("它摔倒了.");
    }
}
