package com.roll.casserole.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author roll
 * created on 2019-07-30 07:57
 */
public class PredicateTest2 {
    public static void main(String[] args) {
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        conditionFilter(integerList, item -> item % 2 == 0);

        conditionFilter(integerList, item -> true);
    }

    public static void conditionFilter(List<Integer> list, Predicate<Integer> predicate) {
        list.forEach(value -> {
            if (predicate.test(value)) {
                System.out.println(value);
            }
        });
    }

}
