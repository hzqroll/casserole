package com.roll.casserole.design.state;

/**
 * @author zongqiang.hao
 * created on 2019-06-25 20:24.
 */
public class ConcreteStateA extends State {
    @Override
    public void handle(Context context) {
        System.out.println("请求 A");
        context.setState(new ConcreteStateB());
    }
}
