package com.roll.casserole.java8;

import java.util.function.Predicate;

/**
 * @author roll
 * created on 2019-07-29 23:11
 */
public class PredicateTest {
    public static void main(String[] args) {
        Predicate<String> predicate = p -> p.length() > 5;

        predicate.test("234");
    }
}
