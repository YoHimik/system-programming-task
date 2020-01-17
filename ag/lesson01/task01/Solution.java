package com.ag.lesson01.task01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
10 класс 21.09
Вывести простые числа до n
 */
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        printPrimeNumber(n);
    }

    public static void printPrimeNumber(int count) {
        int k = 0;
        for (int i = 2; i <= count; i++) {
            for (int j = 1; j <= i; j++) {
                if (i % j == 0)
                    k++;
                if (k > 2)
                    break;
                else if (j == i)
                    System.out.println(i);
            }
            k = 0;
        }
    }
}
