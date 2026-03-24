package com.bezkoder.spring.jpa.h2;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ExercisesTest {

    private static boolean isEven(Integer n) {
        return n % 2 == 0;
    }



    @Test
    void evenOdd(){
        int[] numbers={12, 17, 70, 15, 22, 65, 21, 90};
        Map<Boolean, List<Integer>> collect1 = IntStream.rangeClosed(1, 10).boxed().collect(Collectors.partitioningBy(ExercisesTest::isEven));
        System.out.println("Even Numbers: " + collect1.get(true));
        System.out.println("Odd Numbers: " + collect1.get(false));
        Map<Boolean, List<Integer>> collect = Arrays.stream(numbers).boxed().collect(Collectors.partitioningBy(ExercisesTest::isEven));
        System.out.println("Even Numbers: " + collect.get(true));
        System.out.println("Odd Numbers: " + collect.get(false));
    }

    @Test
    void minValueInArray(){
        int[] numbers={12, 17, 70, 15, 22, 65, 21, 90};
        Optional<Integer> collect = Arrays.stream(numbers).boxed().min(Integer::compareTo);
        System.out.println("minValueInArray: " + collect.get());
    }

    private static int closeTo20(Integer n) {
        return Math.abs(n - 20);
    }

    @Test
    void closeToTarget(){
        int[] numbers={12, 17, 70, 15, 22, 65, 21, 90};
        Optional<Integer> collect = Arrays.stream(numbers).boxed().min(Comparator.comparingInt(ExercisesTest::closeTo20));
        System.out.println("closeToTarget: " + collect.get());
    }
}
