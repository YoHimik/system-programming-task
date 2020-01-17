package com.ag.lesson03.task01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Semen on 29.09.2015.
 * 10 класс
 * Проверить является ли строка полиндромом
 */
public class Solution01 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        System.out.println(Check(s));
    }

    public static String RemoveSpace(String d) {
        String newstring = "";
        char[] chars = d.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ' ') newstring = newstring + chars[i];
        }
        return newstring.toLowerCase();
    }

    public static boolean Check(String d) {
        int count = 0;
        char[] chars = RemoveSpace(d).toCharArray();
        for (int i = 0; i < chars.length / 2; i++) {
            if (chars[i] == chars[chars.length - i - 1]) count++;
            if (chars.length / 2 == count) return true;
        }
        return false;
    }


}
