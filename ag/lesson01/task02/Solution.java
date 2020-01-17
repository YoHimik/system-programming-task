package com.ag.lesson01.task02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
10 класс 21.09
Найти произведение первых n простых чисел
 */
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        ArrayList<Integer> primeNumbers;
        primeNumbers = getFirstNPrimeNumbers(n);
        System.out.println(factorial(primeNumbers));
    }

    public static int factorial(ArrayList<Integer> primeNumbers) {
        int multiply = 1;
        for (Integer primeNumber : primeNumbers) {
            multiply = multiply * primeNumber;
        }
        return multiply;
    }

    public static ArrayList<Integer> getFirstNPrimeNumbers(int n) {
        ArrayList<Integer> primeNumbers = new ArrayList<>();
        int count = 3;
        int k = 0;
        for (int i = 2; i <= count; i++) {
            if (primeNumbers.size() == n)
                break;
            for (int j = 1; j <= i; j++) {
                if (i % j == 0)
                    k++;
                if (k > 2) {
                    count++;
                    break;
                } else if (j == i) {
                    primeNumbers.add(i);
                    count++;
                }
            }
            k = 0;
        }
        return primeNumbers;
    }
}
