package com.bezkoder.spring.jpa.h2.exercices;

import java.util.*;

public class TreeConstructor {

    public static String TreeConstructor(String[] strArr) {
        // code goes here
        List<String[]> nodes = new ArrayList<>();
        for (String s : strArr) {
            String[] node = s.replace("(", "").replace(")", "").split(",");
            nodes.add(node);
        }
        Map<String,Integer> nbrOfChildren = new HashMap<>();
        for (String[] node : nodes) {
            nbrOfChildren.merge(node[1],1,Integer::sum);
            if (nbrOfChildren.get(node[1]) > 2){
                return "false";
            }
        }
        return "true";
    }

    public static void main (String[] args) {
        // keep this function call here

        String[] strArr1 = {"(1,2)", "(2,4)", "(5,7)", "(7,2)", "(9,5)"};
        System.out.println(TreeConstructor(strArr1)); // true
        String[] strArr2 = {"(1,2)", "(3,2)", "(2,12)", "(5,2)"};
        System.out.println(TreeConstructor(strArr2)); // true
    }

}
