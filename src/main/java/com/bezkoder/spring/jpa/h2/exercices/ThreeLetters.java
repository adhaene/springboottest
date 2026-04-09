package com.bezkoder.spring.jpa.h2.exercices;

class ThreeLetters {

    public static void main(String[] args) {
        ThreeLetters unit = new ThreeLetters();
        System.out.println(unit.solution(5,3));
    }
    public String solution(int A, int B) {
        StringBuilder sb = new StringBuilder();

        int a = A, b = B;

        while (a > 0 || b > 0) {
            int len = sb.length();
            boolean putA;

            if (len >= 2 && sb.charAt(len - 1) == sb.charAt(len - 2)) {
                // Last two are the same, must place the other letter
                if (sb.charAt(len - 1) == 'a') {
                    putA = false; // must place 'b'
                } else {
                    putA = true;  // must place 'a'
                }
            } else {
                // Free to choose: place the one with more remaining
                putA = (a >= b);
            }

            if (putA) {
                if (a == 0) {
                    // must place b
                    sb.append('b');
                    b--;
                } else {
                    sb.append('a');
                    a--;
                }
            } else {
                if (b == 0) {
                    // must place a
                    sb.append('a');
                    a--;
                } else {
                    sb.append('b');
                    b--;
                }
            }
        }

        return sb.toString();
    }
}

