package com.roll.casserole.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zongqiang.hao
 * created on 2018/10/25 下午12:01.
 */
public class Car implements Cloneable, Serializable {
    private String name;

    private List<String> wheels;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getWheels() {
        return wheels;
    }

    public void setWheels(List<String> wheels) {
        this.wheels = wheels;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", wheels=" + wheels.toString() +
                '}';
    }

    @Override
    protected Car clone() throws CloneNotSupportedException {
        super.clone();
        Car car = new Car();
        car.name = this.name;
        List<String> newWheel = new ArrayList<>(this.getWheels());
        car.setWheels(newWheel);
        return car;
    }
}
