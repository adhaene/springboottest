package com.bezkoder.spring.jpa.h2.exercices;

import java.util.ArrayList;
import java.util.List;

/*
https://coderbyte.com/editor/First%20Reverse:Java
 */
public class FirstReverse {

    public static void main (String[] args) {

        System.out.println(run("coderbyte"));//etybredoc
        System.out.println(run("I Love Code"));//edoC evoL I
    }

    public static String run(String input) {
        String[] split = input.split(" ");
        for (int i = 0;i< split.length;i++){
            System.out.println(split[i]);
        }
        StringBuilder result = new StringBuilder();
        List<String> list = new ArrayList<>();
        for (String word : split) {
            StringBuilder sb = new StringBuilder().append(word).reverse();
            list.add(sb.toString());
        }
        for (int i = list.size() -1;i>=0;i--){
            result.append(list.get(i)).append(" ");
        }

        return result.toString();
    }

}
