package com.roll.casserole.design.state.demo1;

public class Context {
    private State state;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void request(String str) {
        state.handleState(str);
    }
}
