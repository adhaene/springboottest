package com.bezkoder.spring.jpa.h2.exercices;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toCollection;

public class FindIntersection {

    public static void main (String[] args) {
        // keep this function call here
        String[] strArr = {"1, 3, 4, 7, 13", "1, 2, 4, 13, 15"};

        System.out.println(run(strArr));//1,4,13
        String[] strArr2 = {"1, 3, 9, 10, 17, 18", "1, 4, 9, 10"};

        System.out.println(run(strArr2));
    }

    public static String run(String[] strArr) {

//        TreeSet<Integer> set1 = Arrays.stream(strArr[0].replace(" ", "").split(",")).map(Integer::parseInt).collect(toCollection(TreeSet::new));
//        TreeSet<Integer> set2 = Arrays.stream(strArr[1].replace(" ", "").split(",")).map(Integer::parseInt).collect(toCollection(TreeSet::new));
//        set1.retainAll(set2);
//        return (set1.isEmpty() ? "false" : Arrays.toString(new TreeSet<>(set1).toArray()).replace("]", "").replace("[", "").replace(" ", ""));

        String s1 = strArr[0];
        String s2 = strArr[1];
        ArrayList<String> set1 = Arrays.stream(s1.split(",")).map(String::trim).collect(toCollection(ArrayList::new));
        ArrayList<String> set2 = Arrays.stream(s2.split(",")).map(String::trim).collect(toCollection(ArrayList::new));
        set1.retainAll(set2);
        return (set1.isEmpty() ? "false" : Arrays.toString(new ArrayList<>(set1).toArray()).replace("]", "").replace("[", "").replace(" ", ""));

    }
}
