package com.roll.casserole.design.decorator;

/**
 * @author zongqiang.hao
 * created on 2018/10/25 上午11:13.
 */
public class ConcreteComponent implements Component {
    @Override
    public void printComponent(String name) {
        System.out.println("component " + name);
    }
}
