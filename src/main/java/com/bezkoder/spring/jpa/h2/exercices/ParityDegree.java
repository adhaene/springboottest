package com.bezkoder.spring.jpa.h2.exercices;

public class ParityDegree {

    public static void main(String[] args) {
        ParityDegree parityDegree = new ParityDegree();
        System.out.println(parityDegree.solution(24));
    }

    public int solution(int N) {
        int k = 0;
        while (N % 2 == 0) {
            k++;
            N /= 2;
        }
        return k;
    }
}
