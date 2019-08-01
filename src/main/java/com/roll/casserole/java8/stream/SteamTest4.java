package com.roll.casserole.java8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author roll
 * created on 2019-08-01 08:36
 */
public class SteamTest4 {
    public static void main(String[] args) {
        Stream<String> stringStream = Stream.of("hello", "world", "hello world");
        //String[] arrays = stringStream.toArray(String[]::new);
        //Arrays.asList(arrays).forEach(System.out::println );

        List<String> stringList = stringStream.collect(Collectors.toList());
        stringList = stringStream.collect(() -> new ArrayList(), (theList, item) -> theList.add(item), (theList1, theList2) -> theList1.addAll(theList2));
        stringList = stringStream.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

    }
}
