package com.ag.lesson02.task02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Semen on 27.09.2015.
 * 10 класс
 * Найти косинус угла
 */
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        double x = Double.parseDouble(reader.readLine());
        System.out.print(cos(x));
    }

    public static double cos(double m) {
        return Math.cos(m);
    }
}
