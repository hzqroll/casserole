package com.roll.casserole.jvm.classloader;

/**
 * @author roll
 * created on 2019-11-17 14:29
 */
public class MySample {
    public MySample() {
        System.out.println("MySample is loaded by: " + this.getClass().getClassLoader());

        new MyCat();
    }
}
