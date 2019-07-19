package com.roll.casserole.design.state.demo1;

public class ConcreteStateFinish implements State {
    @Override
    public void handleState(String str) {
        System.out.println("State: finish, str=" + str);
    }
}
