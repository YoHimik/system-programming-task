package com.ag.lesson02.task01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Semen on 27.09.2015.
 * 10 класс
 * Вывести факториал через рекурсию
 */
public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        System.out.println(factorial(n));
    }

    public static int factorial(int m) {
        if (m != 1)
            return (m * factorial(m - 1));
        return m;
    }
}
