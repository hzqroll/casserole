package com.roll.casserole.java8.stream.group;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author roll
 * created on 2019-08-04 15:31
 */
public class StreamTest13 {
    public static void main(String[] args) {
        Student student1 = new Student("zhangsan", 100, 29);
        Student student2 = new Student("zhangsan1", 90, 20);
        Student student3 = new Student("zhangsan2", 90, 28);
        Student student4 = new Student("zhangsan", 80, 40);

        List<Student> students = Arrays.asList(student1, student2, student3, student4);

        // 根据name进行分组
        //{zhangsan1=[com.roll.casserole.java8.stream.group.Student@48533e64], zhangsan2=[com.roll.casserole.java8.stream.group.Student@64a294a6], zhangsan=[com.roll.casserole.java8.stream.group.Student@7e0b37bc], zhangsan4=[com.roll.casserole.java8.stream.group.Student@3b95a09c]}
        //Map<String, List<Student>> map = students.stream().collect(Collectors.groupingBy(Student::getName));

        //{zhangsan1=1, zhangsan2=1, zhangsan=2}
        //Map<String, Long> map = students.stream().collect(Collectors.groupingBy(Student::getName, Collectors.counting()));

        //{zhangsan1=90.0, zhangsan2=90.0, zhangsan=90.0}
//        Map<String, Double> map = students.stream().collect(Collectors.groupingBy(Student::getName, Collectors.averagingDouble(Student::getScore)));

        //{false=[com.roll.casserole.java8.stream.group.Student@64a294a6, com.roll.casserole.java8.stream.group.Student@7e0b37bc, com.roll.casserole.java8.stream.group.Student@3b95a09c], true=[com.roll.casserole.java8.stream.group.Student@6ae40994]}
        //Map<Boolean, List<Student>> map = students.stream().collect(Collectors.partitioningBy(student -> student.getScore() > 90));

        //System.out.println(map);
        students.stream().forEach(System.out::println);

    }
}
