package com.roll.casserole.design.proxy;

import java.lang.reflect.Proxy;

/**
 * @author zongqiang.hao
 * created on 2019-02-21 11:19.
 */
public class DogTest {
    public static void main(String arsg[]) throws Throwable {
        test1();
        System.out.println("------------------------ -----------------");
        test2();

        test3();
    }

    private static void test1() {
        Pomeranian pomeranian = new Pomeranian();
        FunDog funDog = new FunDog(pomeranian);
        PomeranianTime pomeranianTime = new PomeranianTime(funDog);
        pomeranianTime.run();
    }

    private static void test2() {
        Pomeranian pomeranian = new Pomeranian();
        PomeranianTime pomeranianTime = new PomeranianTime(pomeranian);
        FunDog funDog = new FunDog(pomeranianTime);
        funDog.run();
    }

    private static void test3() throws Throwable {
        Pomeranian pomeranian = new Pomeranian();
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(pomeranian);
        myInvocationHandler.invoke(Proxy.getProxyClass(null, Pomeranian.class), null, null);
    }
}
