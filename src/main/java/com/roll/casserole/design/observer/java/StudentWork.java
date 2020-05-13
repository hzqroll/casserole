package com.roll.casserole.design.observer.java;

import java.util.EventListener;

/**
 * @author zongqiang.hao
 * created on 2020/5/8 9:08 下午.
 */
public class StudentWork implements EventListener {
    public void doHomeWork(Teacher teacher) {
        System.out.println("完成老师" + teacher.getName() + "的作业");
    }
}
