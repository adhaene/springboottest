package com.bezkoder.spring.jpa.h2.exercices;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodelandUsernameValidation {

    public static String run(String str) {
        Pattern pattern = Pattern.compile("^[A-Za-z][0-9A-Za-z_]{2,23}[0-9A-Za-z]$");
        Matcher matcher = pattern.matcher(str);
        return matcher.find() ? "true" : "false";
    }
    public static void main (String[] args) {
        // keep this function call here
        System.out.println(run("aa_"));
        System.out.println(run("u__hello_world123"));
    }
}
