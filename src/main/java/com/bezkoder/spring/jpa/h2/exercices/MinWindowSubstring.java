package com.bezkoder.spring.jpa.h2.exercices;

import java.util.HashSet;
import java.util.Set;
import java.util.*;

public class MinWindowSubstring {
    public static String MinWindowSubstring(String[] strArr) {
        // code goes here
        String s1  = strArr[0];
        String s2  = strArr[1];
        for (int i =0;i<=s1.length()-s2.length();i++){
            for(int j =0;j<=s1.length()-s2.length() - i;j++){
                //System.out.println(i + " " + j);
                String s3 = s1.substring(j, s2.length() + j + i);
                //System.out.println(s3);

                if (s3.length() >= s2.length()) {
                    if (bigContainsSmall(s2, s3)) {
                        return s3;  //aksfaje //affhkkse
                    }
                }
            }
        }

        return null;
    }

    private static boolean bigContainsSmall(String small, String big) {
        char[] charArray = small.toCharArray();
        StringBuilder sb = new StringBuilder();
        sb.append(big);
        boolean valid = true;
        for (char c : charArray) {
            int indexOf = sb.indexOf(String.valueOf(c));
            if(indexOf == -1){
                valid = false;
                break;
            } else {
                sb.deleteCharAt(indexOf);
            }
        }

        return valid;
    }


    public static void main (String[] args) {
        // keep this function call here
        //Scanner s = new Scanner(System.in);
                String[] strArr5 = {"ahffaksfajeeubsne", "jefaa"};
        System.out.println(MinWindowSubstring(strArr5)); // aksfaje

        String[] strArr = {"aaabaaddae", "aad"};
        System.out.println(MinWindowSubstring(strArr));
        //Input: new String[] {"ahffaksfajeeubsne", "jefaa"}
        //Output: aksfaje
//        Input: new String[] {"aaffhkksemckelloe", "fhea"}
//        Output: affhkkse
        String[] strArr2 = {"ahffaksfajeeubsne", "jefaa"};
        System.out.println(MinWindowSubstring(strArr2));  //aksfaje
        String[] strArr3 = {"aaffhkksemckelloe", "fhea"};
        System.out.println(MinWindowSubstring(strArr3)); //affhkkse
    }

    public static int solution(int[] A) {
        Set<Integer> seen = new HashSet<>();
        for(int num : A){
            if(num>0){
                seen.add(num);
            }
        }
        int smallest = 1;
        while (seen.contains(smallest)){
            smallest++;
        }
        return smallest;
    }


}