package com.roll.casserole.java8;

/**
 * @author roll
 * created on 2019-07-29 22:27
 */
public class Person {
    String userName;
    int age;

    public Person(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
