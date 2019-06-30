package com.roll.casserole.design.decorator;

/**
 * @author zongqiang.hao
 * created on 2018/10/25 上午11:14.
 */
public class Decorator implements Component {

    private Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void printComponent(String name) {
        component.printComponent(name);
    }
}
