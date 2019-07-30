package com.roll.casserole.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * @author roll
 * created on 2019-07-29 22:28
 */
public class PersonTest {
    public static void main(String[] args) {
        Person person1 = new Person("a", 1);
        Person person2 = new Person("b", 1);
        Person person3 = new Person("c", 1);

        List<Person> perons = Arrays.asList(person1, person2, person3);


    }

    public List<Person> getPersonByUserName(String userName, List<Person> people) {
        return people.stream().filter(person -> person.userName.equals(userName)).collect(Collectors.toList());
    }

    public List<Person> getPersonByAge(int age, List<Person> personList) {
        BiFunction<Integer, List<Person>, List<Person>> biFunction = (ageOfPerson, persons) -> persons.stream().filter(person -> person.getAge() > ageOfPerson).collect(Collectors.toList());
        return biFunction.apply(age, personList);
    }


}
