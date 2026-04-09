package com.bezkoder.spring.jpa.h2.exercices;


class StrSymmetryPoint {

    public static void main(String[] args) {

        StrSymmetryPoint unit = new StrSymmetryPoint();
        System.out.println(unit.solution("racecar"));
        System.out.println(unit.solution("x"));
        StringBuilder sb = new StringBuilder();
        System.out.println(sb.append("abba").reverse());
        if(sb.append("abba").reverse().equals("abba")){
            System.out.println("ss");
        }
    }
    public int solution(String S) {
        int n = S.length();
        if (n % 2 == 0) {
            return -1; // no middle character
        }

        int mid = n / 2;

        // Compare left side with reversed right side
        for (int i = 0; i < mid; i++) {
            if (S.charAt(i) != S.charAt(n - 1 - i)) {
                return -1;
            }
        }

        return mid;
    }
}

