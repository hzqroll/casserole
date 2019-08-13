package com.roll.casserole.java8.Stream2;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class MySetCollector2<T> implements Collector<T, Set<T>, Map<T,T>> {
    @Override
    public Supplier<Set<T>> supplier() {
        System.out.println("supp;ier invoked!");
        return HashSet<T>::new;
    }

    @Override
    public BiConsumer<Set<T>, T> accumulator() {
        System.out.println("accmulator invoked!");
        return (set, item)->{
            System.out.println("acc");
            set.add(item);
        };
    }

    @Override
    public BinaryOperator<Set<T>> combiner() {
        System.out.println("combinder invoked!");
        return (set1, set2)->{set1.addAll(set2); return set1;};
    }

    @Override
    public Function<Set<T>, Map<T, T>> finisher() {
        System.out.println("finisher invoked!");

        return (set)->{
            Map<T, T> map = new HashMap<>();
            set.stream().forEach(item->map.put(item, item));
            return  map;
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        System.out.println("characteristics invoked!");

        return Collections.unmodifiableSet(EnumSet.of(Characteristics.UNORDERED, Characteristics.CONCURRENT));
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("a","b","c");

        Set<String> set = new HashSet<>();
        set.addAll(list);

        Map<String, String> map = set.stream().collect(new MySetCollector2<>());
        System.out.println(map);
    }
}
