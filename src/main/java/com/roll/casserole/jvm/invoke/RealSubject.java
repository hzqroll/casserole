package com.roll.casserole.jvm.invoke;

/**
 * @author roll
 * created on 2020/3/25 8:48 上午
 */
public class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("from real subject");
    }
}
