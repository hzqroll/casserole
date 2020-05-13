package com.roll.casserole.design.observer.java;

import java.util.EventObject;

/**
 * @author zongqiang.hao
 * created on 2020/5/8 9:07 下午.
 */
public class HomeWorkEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public HomeWorkEvent(Teacher source) {
        super(source);
    }

    @Override
    public Object getSource() {
        return super.getSource();
    }
}
