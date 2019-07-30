package com.roll.casserole.java8;

/**
 * @author roll
 * created on 2019-07-27 21:03
 */
@FunctionalInterface
interface MyInterface {
    // 唯一的抽象方法
    void test2();

    String toString();
}

public class Test2 {
    public void myTest(MyInterface myInterface) {
        myInterface.test2();
    }

    public static void main(String[] args) {
        Test2 test2 = new Test2();
        test2.myTest(new MyInterface() {
            @Override
            public void test2() {
                System.out.println("traditional test2");
            }
        });

        test2.myTest(() -> {System.out.println("lambda test2");});

        MyInterface myInterface = () -> {
            System.out.println("traditional test2");
        };
        // class com.roll.casserole.java8.Test2$$Lambda$2/485815673
        System.out.println(myInterface.getClass());

    }


}
