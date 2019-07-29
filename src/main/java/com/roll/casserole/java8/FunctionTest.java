package com.roll.casserole.java8;

import java.util.function.Function;

/**
 * @author roll
 * created on 2019-07-29 08:00
 */
public class FunctionTest {
    public static void main(String[] args) {
        FunctionTest functionTest = new FunctionTest();

        System.out.println(functionTest.compute(1, value -> {
            return 2 * value;
        }));

        System.out.println(functionTest.compute(1, value -> 2 * value));
    }

    public int compute(int n, Function<Integer, Integer> function) {
        int result = function.apply(n);
        return result;
    }
}
