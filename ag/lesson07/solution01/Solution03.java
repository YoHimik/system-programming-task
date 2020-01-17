package com.ag.lesson07.solution01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Semen on 12.03.2015.
 * 10 класс
 * Генератор перестановок, перстановок с повторениями, сочетания, сочетания с повторениями
 **/

public class Solution04 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int k = Integer.parseInt(reader.readLine());
        ArrayList<Integer> placements = findPlacements(n, k);
        System.out.println(placements);
        ArrayList<Integer> combinations = findCombinations(placements);
        System.out.println(combinations);
        System.out.println(findPlacementsWithRepeats(n, k));
    }

    public static ArrayList<Integer> findPlacements(int n, int k) {
        int count = findFactorial(n) / findFactorial(n - k);
        int firstNumber = liftUpDegree(10, k - 1);
        int q;
        int w = 0;
        boolean tester = false;
        ArrayList<Integer> placements = new ArrayList<Integer>();
        int[] combination = new int[k];
        int[] number = new int[n];
        for (int i = 1; i < n + 1; i++) {
            number[i - 1] = i;
        }
        if (k == 1) {
            for (int i = 0; i < number.length; i++) {
                placements.add(number[i]);
            }
            return placements;
        }
        while (placements.size() != count) {
            q = firstNumber;
            for (int i = combination.length - 1; i > -1; i--) {
                combination[i] = q % 10;
                q = q / 10;
            }
            for (int i = 0; i < combination.length; i++) {
                for (int j = 0; j < combination.length; j++) {
                    if ((combination[i] == combination[j]) && (j != i)) {
                        tester = true;
                        break;
                    }
                }
                if (tester) break;
            }
            for (int i = 0; i < number.length; i++) {
                for (int j = 0; j < combination.length; j++) {
                    if (number[i] == combination[j]) {
                        w++;
                        break;
                    }
                }
                if (tester) break;
            }
            if ((!tester) && (w == k)) {
                placements.add(firstNumber);
                tester = false;
                firstNumber++;
                w = 0;
            } else {
                tester = false;
                firstNumber++;
                w = 0;
            }
        }
        return placements;
    }

    public static ArrayList<Integer> findPlacementsWithRepeats(int n, int k) {
        ArrayList<Integer> pwr = new ArrayList<Integer>();
        int maxNumber = liftUpDegree(10, k);
        int minNumber = liftUpDegree(10, k - 1);
        for (int i = minNumber + 1; i < maxNumber; i++) {
            pwr.add(i);
        }
        for (int i = 0; i < pwr.size(); i++) {
            if (findProductOfNumbers(pwr.get(i)) == 0) {
                pwr.remove(i);
                i--;
            }
        }
        if ((n == 9) && (k == 9)) return pwr;
        else {
            for (int i = 0; i < pwr.size(); i++) {
                for (int j = 0; j < brakeNumber(pwr.get(i)).size(); j++) {
                    if (brakeNumber(pwr.get(i)).get(j) > n) {
                        pwr.remove(i);
                        i--;
                        break;
                    }
                }
            }
        }
        return pwr;
    }

    public static ArrayList<Integer> findCombinations(ArrayList<Integer> combinations) {
        ArrayList<Integer> newCombinations = new ArrayList<Integer>();
        for (int i = 0; i < combinations.size(); i++) {
            if (combinations.get(i) != 0) {
                for (int j = i + 1; j < combinations.size(); j++) {
                    if (combinations.get(j) != 0) {
                        if ((findProductOfNumbers(combinations.get(j)) == findProductOfNumbers(combinations.get(i))) && (findSumOfNumbers(combinations.get(j)) == findSumOfNumbers(combinations.get(i)))) {
                            combinations.set(j, 0);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < combinations.size(); i++) {
            if (combinations.get(i) != 0) newCombinations.add(combinations.get(i));
        }
        return newCombinations;
    }

    public static int findFactorial(int n) {
        int k = n;
        if (n == 0) return 1;
        for (int i = 1; i < k; i++) {
            n = n * i;
        }
        return n;
    }

    public static int liftUpDegree(int n, int k) {
        if (k == 0) return 1;
        int a = n;
        for (int i = 0; i < k - 1; i++) {
            a = a * n;
        }
        return a;
    }

    public static int findProductOfNumbers(int n) {
        int a = 1;
        while (n != 0) {
            a = a * (n % 10);
            n = n / 10;
        }
        return a;
    }

    public static int findSumOfNumbers(int n) {
        int a = 0;
        while (n != 0) {
            a = a + (n % 10);
            n = n / 10;
        }
        return a;
    }

    public static ArrayList<Integer> brakeNumber(int n) {
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        while (n != 0) {
            numbers.add(n % 10);
            n = n / 10;
        }
        return numbers;
    }


}
