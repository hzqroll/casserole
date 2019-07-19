package com.roll.casserole.design.state.demo1;

/**
 * 具体状体类
 */
public class ConcreteStateWating implements State {
    @Override
    public void handleState(String str) {
        System.out.println("State: wating, str=" + str);
    }
}
