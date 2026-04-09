package com.bezkoder.spring.jpa.h2.exercices;

public class Test {
    public static void main(String[] args) {
        int[] A = {-1,6,3,4,7,4};

        ArrayInversionCount unit = new ArrayInversionCount();
        System.out.println(unit.solution(A));
    }
}
