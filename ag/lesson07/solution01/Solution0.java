package com.ag.lesson07.solution01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Semen on 18.12.2015.
 * 10 класс
 * Разложить число на множители, используя решено эрастофена
 */

public class Solution0 {

    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        System.out.println(factorization(n));
    }

    public static ArrayList<Integer> eratosthenes(int n){
        ArrayList<Integer> primeNumbers = new ArrayList<Integer>();
        for (int i = 2; i < n + 1; i++) {
            primeNumbers.add(i);
        }
        for (int i = 0; i < primeNumbers.size(); i++) {
            for (int j = i + 1; j < primeNumbers.size(); j++) {
                if(primeNumbers.get(j) % primeNumbers.get(i) == 0) primeNumbers.remove(j);
            }
        }
        return  primeNumbers;
    }

    public static ArrayList<Integer> factorization(int n){
        ArrayList<Integer> primeNumbers = new ArrayList<Integer>();
        primeNumbers.add(1);
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < primeNumbers.size(); i++) {
            primeNumbers = eratosthenes(n);
            for (int j = 0; j < primeNumbers.size(); j++) {
                if(n % primeNumbers.get(j) == 0){
                    n = n / primeNumbers.get(j);
                    result.add(primeNumbers.get(j));
                    break;
                }
            }
        }
        return result;
    }
}
