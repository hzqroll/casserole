package com.roll.casserole.jvm.classloader;

/**
 * @author roll
 * created on 2019-11-17 14:28
 */
public class MyCat {
    public MyCat() {
        System.out.println("MyCat is loaded by: " + this.getClass().getClassLoader());

        System.out.println("from myCat " + MySample.class);
    }
}
