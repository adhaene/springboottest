package com.bezkoder.spring.jpa.h2.exercices;

public class ArrListLen {

    public static void main(String[] args) {
        int[] A = {1,4,5,2,-1,3,2};
        ArrListLen arrListLen = new ArrListLen();
        System.out.println(arrListLen.solution(A));
    }
    public int solution(int[] A) {
        int count = 0;
        int index = 0;

        while (index != -1) {
            count++;
            index = A[index];
        }

        return count;
    }
}
