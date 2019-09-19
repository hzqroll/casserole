
package com.roll.casserole.netty.serialization;

import java.io.Serializable;
import java.util.Date;

public class Person implements Serializable {
    private String name;
    private int age;
    private boolean pass;
    private double score;
    private Date time;

    public Person(String name, int age, boolean pass, Date time) {
        this.name = name;
        this.age = age;
        this.pass = pass;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", pass=" + pass +
                ", score=" + score +
                ", time=" + time +
                '}';
    }
}