package com.ag.lesson04.task02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Semen on 12.10.2015.
 * 10 класс
 * Быстрое возведение в степень
 */
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        long a = Long.parseLong(reader.readLine());
        long b = Long.parseLong(reader.readLine());
        System.out.print(degree(a, b));
    }

    public static long degree(long a, long b) {
        if (b == 1) return a;
        if (b % 2 == 0) return degree(a*a, b /2);
        return a * degree(a, b - 1);
    }
}
