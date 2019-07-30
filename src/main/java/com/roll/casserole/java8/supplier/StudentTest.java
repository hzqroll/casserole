package com.roll.casserole.java8.supplier;

import java.util.function.Supplier;

/**
 * @author roll
 * created on 2019-07-30 08:37
 */
public class StudentTest {
    public static void main(String[] args) {
        Supplier<Student> studentSupplier = ()->new Student();

        Supplier<Student> studentSupplier1 = Student::new;
    }
}
