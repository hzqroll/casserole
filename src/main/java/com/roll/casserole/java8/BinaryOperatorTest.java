package com.roll.casserole.java8;

import java.util.function.BinaryOperator;

/**
 * @author roll
 * created on 2019-07-30 08:45
 */
public class BinaryOperatorTest {
    public static void main(String[] args) {
        compute(1, 2, (a, b) -> a + b);
    }

    public static int compute(int a, int b, BinaryOperator<Integer> binaryOperator) {
        return binaryOperator.apply(a, b);
    }
}
