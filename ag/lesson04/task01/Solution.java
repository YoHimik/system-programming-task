package com.ag.lesson04.task01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Semen on 6.10.2015.
 * 10 класс
 * Длинное деление с остатком
 */

public class Solution {

    static ArrayList<Integer> residue = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String number1 = reader.readLine();
        String number2 = reader.readLine();

        ArrayList<Integer> n = new ArrayList<>();
        n.add(0);

        int[] number1Array = new int[number1.length()];
        int[] number2Array = new int[number2.length()];

        for (int i = 0; i < number1.length(); i++) {
            number1Array[i] = Integer.parseInt(Character.toString(number1.charAt(i)));
        }
        for (int i = 0; i < number2.length(); i++) {
            number2Array[i] = Integer.parseInt(Character.toString(number2.charAt(i)));
        }

        ArrayList<Integer> q = divison(number1Array, number2Array, n);
        for (int i = 1; i < q.size() + 1; i++) {
            System.out.print(q.get(q.size() - i));
        }
        System.out.print(";");
        for (int i = 1; i < residue.size() + 1; i++) {
            System.out.print(residue.get(residue.size() - i));
        }
    }

    public static ArrayList<Integer> divison(int[] array, int[] array2, ArrayList<Integer> n) {
        while (searchMoreNumber(array, array2) == true) {
            array = difference(array, array2);
            n = heapOne(n);
        }
        for (int i = 1; i < array.length + 1; i++) {
            residue.add(array[array.length - i]);
        }
        return n;
    }

    public static int[] difference(int[] array, int[] array2) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 1; i < array2.length + 1; i++) {
            if (array[array.length - i] - array2[array2.length - i] >= 0) {
                result.add(array[array.length - i] - array2[array2.length - i]);
            } else {
                for (int j = i + 1; j < array.length + 1; j++) {
                    if (array[array.length - j] == 0) array[array.length - j] = 9;
                    else if (array[array.length - j] > 0) {
                        array[array.length - j] = array[array.length - j] - 1;
                        break;
                    }
                }
                result.add(array[array.length - i] + 10 - array2[array2.length - i]);
            }
        }
        if (array.length != array2.length) {
            for (int i = array.length - array2.length - 1; i != -1; i--) {
                result.add(array[i]);
            }
        }
        if (result.get(result.size() - 1) == 0) result.remove(result.size() - 1);
        int[] n = new int[result.size()];

        for (int i = 1; i < result.size() + 1; i++) {
            n[i - 1] = result.get(result.size() - i);
        }
        return n;
    }

    public static boolean searchMoreNumber(int[] array, int[] array2) {
        if (array.length > array2.length) return true;
        else if (array2.length > array.length) return false;
        else {
            for (int i = 0; i < array.length; i++) {
                if (array[i] != array2[i]) {
                    if (array[i] > array2[i]) return true;
                    else return false;
                }
            }
        }
        return true;
    }

    public static ArrayList<Integer> heapOne(ArrayList<Integer> n) {
        boolean b = false;
        for (int i = 0; i < n.size(); i++) {
            if (n.get(i) == 9) {
                n.set(i, 0);
            } else {
                n.set(i, n.get(i) + 1);
                b = true;
                break;
            }
        }
        if (!b) {
            for (int j = 0; j < n.size(); j++) {
                n.set(j, 0);
            }
            n.add(1);
        }
        return n;
    }
}

