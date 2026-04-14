package com.bezkoder.spring.jpa.h2.exercices;

import java.util.ArrayList;
import java.util.List;

/*
https://coderbyte.com/editor/First%20Factorial:Java
 */
public class FirstFactorial {

    public static void main (String[] args) {

        System.out.println(run(4));//24
        System.out.println(run(8));//40320
    }

    public static int run(int input) {
        if (input != 1){
            return input * run(input-1);
        }
        return input;
    }

}
