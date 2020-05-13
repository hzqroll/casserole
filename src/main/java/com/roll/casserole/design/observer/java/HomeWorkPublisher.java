package com.roll.casserole.design.observer.java;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zongqiang.hao
 * created on 2020/5/8 9:12 下午.
 */
public class HomeWorkPublisher {
    private List<StudentWork> studentWorks = new ArrayList<>();

    private void addListener(StudentWork studentWork) {
        studentWorks.add(studentWork);
    }

    public void publish(Teacher teacher) {
        studentWorks.forEach(studentWork -> studentWork.doHomeWork(teacher));
    }

    public static void main(String[] args) {
        HomeWorkPublisher homeWorkPublisher = new HomeWorkPublisher();
        Teacher teacher = new Teacher();
        homeWorkPublisher.addListener(new StudentWork());
        homeWorkPublisher.publish(teacher);
    }
}
