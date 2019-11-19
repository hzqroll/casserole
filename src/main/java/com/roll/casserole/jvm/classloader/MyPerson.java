package com.roll.casserole.jvm.classloader;

/**
 * @author roll
 * created on 2019-11-17 21:52
 */
public class MyPerson {

    private MyPerson myPerson;

    public void setMyPerson(Object object) {
        this.myPerson = (MyPerson) object;
    }
}
