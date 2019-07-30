package com.roll.casserole.java8.supplier;

/**
 * @author roll
 * created on 2019-07-30 08:36
 */
public class Student {
    String name = "zhangsan";
    int age = 20;

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
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
}
