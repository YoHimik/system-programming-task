package com.ag.lesson03.task02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Semen on 04.10.2015.
 * 10 класс
 * Удалить из строки лишние пробелы и перевести первые слова в вверхний регистр
 */
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        System.out.println(UpLetter(s));
    }

    public static String DeleteSpace(String s) {
        int count = 0;
        s = s.trim();
        String newstring = "";
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length - 1; ) {
            if (chars[i] == ' ' && chars[i + 1] == ' ')
                i++;
            else {
                newstring = newstring + chars[i];
                if (i == chars.length - 2) newstring = newstring + chars[i + 1];
                i++;
            }
        }
        return newstring.toLowerCase();
    }

    public static String UpLetter(String s) {

        char[] chars = DeleteSpace(s).toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        String newString = "" + chars[0];
        for (int i = 1; i < chars.length - 1; i++) {
            if (chars[i] == ' ') chars[i + 1] = Character.toUpperCase(chars[i + 1]);
            newString = newString + chars[i];
            if (i == chars.length - 2)
                newString = newString + chars[i + 1];
        }
        return newString;
    }
}
