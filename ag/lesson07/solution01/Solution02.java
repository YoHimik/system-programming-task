package com.ag.lesson07.solution01;

/**
 * Created by Semen on 4.02.2015.
 * 10 класс
 * Quine
 */


public class Solution02 {
    public static void main(String[] args) {
        String s = "public class Solution02 {%3$c%4$cpublic static void main (String[] args) {%3$c%4$c%4$cString s = %2$c%1$s%2$c;%3$c%4$c%4$cSystem.out.printf(s, s, 34, 10, 9);%3$c%4$c}%3$c}";
        System.out.printf(s, s, 34, 10, 9);
    }
}


