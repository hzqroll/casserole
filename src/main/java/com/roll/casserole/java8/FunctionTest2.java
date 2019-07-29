package com.roll.casserole.java8;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author roll
 * created on 2019-07-29 08:17
 */
public class FunctionTest2 {
    public static void main(String[] args) {
        FunctionTest2 functionTest2 = new FunctionTest2();

        System.out.println(functionTest2.compute1(2, value -> value * 3, value -> value * value));
        System.out.println(functionTest2.compute2(2, value -> value * 3, value -> value * value));

        System.out.println(functionTest2.compute3(1, 2, (value1, value2) -> value1 * value2));

        System.out.println(functionTest2.compute4(2, 3, (value2, value3) -> value2 + value2, value -> value * value));
    }

    public int compute1(int a, Function<Integer, Integer> function1, Function<Integer, Integer> function2) {
        return function1.compose(function2).apply(a);
    }

    public int compute2(int a, Function<Integer, Integer> function1, Function<Integer, Integer> function2) {
        return function1.andThen(function2).apply(a);
    }

    public int compute3(int a, int b, BiFunction<Integer, Integer, Integer> biFunction) {
        return biFunction.apply(a, b);
    }

    public int compute4(int a, int b, BiFunction<Integer, Integer, Integer> biFunction, Function<Integer, Integer> function) {
        return biFunction.andThen(function).apply(a, b);
    }
}
