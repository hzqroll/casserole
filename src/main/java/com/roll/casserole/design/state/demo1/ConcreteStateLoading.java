package com.roll.casserole.design.state.demo1;

public class ConcreteStateLoading implements State {
    @Override
    public void handleState(String str) {
        System.out.println("State: loading, str=" + str);
    }
}
