package com.bezkoder.spring.jpa.h2.exercices;

class LongestPassword {

    public static void main(String[] args) {
        int[] A = {-1,6,3,4,7,4};

        LongestPassword unit = new LongestPassword();
        System.out.println(unit.solution("test 5 a0A pass007 ?xy1"));
    }
    public int solution(String S) {
        String[] words = S.split(" ");
        int best = -1;

        for (String w : words) {
            if (isValid(w)) {
                best = Math.max(best, w.length());
            }
        }

        return best;
    }

    private boolean isValid(String w) {
        int letters = 0;
        int digits = 0;

        for (char c : w.toCharArray()) {
            if (Character.isLetter(c)) {
                letters++;
            } else if (Character.isDigit(c)) {
                digits++;
            } else {
                return false; // non‑alphanumeric → invalid
            }
        }

        return letters % 2 == 0 && digits % 2 == 1;
    }
}
