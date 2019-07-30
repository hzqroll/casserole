package com.roll.casserole.java8.supplier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author roll
 * created on 2019-07-30 08:36
 */
public class Student {
    String name = "zhangsan";
    int age = 20;

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static int compareByName(Student student1, Student student2) {
        return student1.getName().compareTo(student2.getName());
    }

    public int compareByName2(Student student) {
        return this.getName().compareTo(student.getName());
    }

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.sort(Student::compareByName);

        List<String> strings = Arrays.asList("a", "b");
        Collections.sort(strings, String::compareTo);
        strings.forEach(System.out::println);
    }

}
