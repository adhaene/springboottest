package com.bezkoder.spring.jpa.h2.exercices;


class TennisTournament {

    public static void main(String[] args) {
        TennisTournament unit = new TennisTournament();
        System.out.println(unit.solution(5,3));
        System.out.println(unit.solution(10,3));
    }

    public int solution(int P, int C) {
        return Math.min(P / 2, C);
    }
}
