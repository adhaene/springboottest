package com.bezkoder.spring.jpa.h2.exercices;

public class Test {
    public static void main(String[] args) {
        Test unit = new Test();
        System.out.println(unit.solution(2));
    }

    public String solution(int digits) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i<= digits;i++){
            sb.append(1);
        }
        //System.out.println(sb);
        Integer previousValue = Integer.valueOf(sb.toString());
        previousValue++;
        while (previousValue.toString().length()==digits){
            char[] charArray = previousValue.toString().toCharArray();
            char c = charArray[0];
            boolean valid =true;
            for (int i = 1; i <= charArray.length-1;i++){
                if ((int) charArray[i] < (int) c){
                    valid = false;
                }
            }
            if(!valid){
                previousValue++;
                continue;
            }
            sb.append(",");
            sb.append(previousValue);
            previousValue++;
        }
        //System.out.println(sb);

        return sb.toString();
    }
}
