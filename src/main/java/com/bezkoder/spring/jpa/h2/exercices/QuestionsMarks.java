package com.bezkoder.spring.jpa.h2.exercices;

/*
https://coderbyte.com/editor/Questions%20Marks:Java
 */
public class QuestionsMarks {

    public static void main (String[] args) {

        System.out.println(run("aa6?9"));//false
        System.out.println(run("acc?7??sss?3rr1??????5"));//true
    }

    public static String run(String input) {
        int i = 0;
        while(i < input.length()) {
            if(Character.isDigit(input.charAt(i))) {
                int j = i + 1;
                int qCount = 0;
                while(j < input.length()) {
                    if(input.charAt(j) == '?') qCount++;
                    if(Character.isDigit(input.charAt(j))) {
                        int sum = Character.getNumericValue(input.charAt(i)) + Character.getNumericValue(input.charAt(j));
                        if((sum == 10 && qCount == 3)){
                            return "true";
                        }
                        i = j;
                        break;
                    }
                    j++;
                }
            }
            i++;
        }
        return "false";
    }
}
