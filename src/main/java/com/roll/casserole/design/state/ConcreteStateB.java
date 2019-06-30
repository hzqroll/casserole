package com.roll.casserole.design.state;

/**
 * @author zongqiang.hao
 * created on 2019-06-25 20:25.
 */
public class ConcreteStateB extends State {
    @Override
    public void handle(Context context) {
        System.out.println("请求 B");
        context.setState(new ConcreteStateA());
    }
}
