package com.roll.casserole.todel;

import java.math.BigInteger;
import java.util.*;

/**
 * @author roll
 * created on 2019-12-06 16:24
 */
public class CollectionClassifier {
    public static String classify(Set<?> set) {
        return "Set";
    }

    public static String classify(Collection<?> set) {
        return "Unknown Collection";
    }

    public static String classify(List<?> set) {
        return "List";
    }

    public static void main(String[] args) {
        Collection<?>[] collections = {
                new HashSet<String>(),
                new ArrayList<BigInteger>(),
                new HashMap<String, String>().values()};

        for (Collection<?> collection : collections) {
            System.out.println(classify(collection));
        }
    }
}
