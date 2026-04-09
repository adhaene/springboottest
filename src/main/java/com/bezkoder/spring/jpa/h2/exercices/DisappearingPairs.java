package com.bezkoder.spring.jpa.h2.exercices;

class DisappearingPairs {

    public static void main(String[] args) {

        DisappearingPairs unit = new DisappearingPairs();
        System.out.println(unit.solution("ACCAABBC"));
        System.out.println(unit.solution("ABCBBCBA"));
        System.out.println(unit.solution("BABABA"));
    }
    public String solution(String S) {
        StringBuilder stack = new StringBuilder();

        for (char c : S.toCharArray()) {
            int n = stack.length();
            if (n > 0 && stack.charAt(n - 1) == c) {
                // Found AA, BB, or CC → remove the pair
                stack.deleteCharAt(n - 1);
            } else {
                stack.append(c);
            }
        }

        return stack.toString();
    }
}
