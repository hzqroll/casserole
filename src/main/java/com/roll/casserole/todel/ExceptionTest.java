package com.roll.casserole.todel;

import com.roll.casserole.utils.Car;
import org.checkerframework.checker.units.qual.C;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zongqiang.hao
 * created on 2019-06-18 19:57.
 */
public class ExceptionTest {
    public static void main(String[] args) {
        /*Map<String, String> testMap = new ConcurrentHashMap<>();
        if (testMap.containsKey(null)) {
            System.out.println("not caints");
            testMap.get(null);
        }*/

        ExceptionTest exceptionTest = new ExceptionTest();
        exceptionTest.test();
        System.gc();
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Car test() {
        Car car = new Car();
        return car;
    }
}
