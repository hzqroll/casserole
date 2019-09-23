
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
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