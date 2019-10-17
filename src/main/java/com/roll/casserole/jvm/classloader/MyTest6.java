package com.roll.casserole.jvm.classloader;

/**
 * @author roll
 * created on 2019-10-17 08:22
 */
public class MyTest6 {
    public static void main(String[] args) {
        Singleton singleton = Singleton.getIntance();
        System.out.println(Singleton.count1);
        System.out.println(Singleton.count2);


        Singleton1 singleton1 = Singleton1.getIntance();
        System.out.println(Singleton1.count1);
        System.out.println(Singleton1.count2);
    }
}

class Singleton {
    public static int count1;

    public static int count2 = 0;

    public static Singleton singleton = new Singleton();

    private Singleton() {
        count1++;
        count2++;
    }

    public static Singleton getIntance() {
        return singleton;
    }
}

class Singleton1 {
    public static int count1;

    public static Singleton1 singleton1 = new Singleton1();

    private Singleton1() {
        count1++;
        count2++;
    }

    public static int count2 = 0;

    public static Singleton1 getIntance() {
        return singleton1;
    }
}