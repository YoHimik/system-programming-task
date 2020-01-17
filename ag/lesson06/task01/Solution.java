package com.ag.lesson06.task01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


/**
 * Created by Semen on 18.12.2015.
 * 10 класс
 * Поиск в ширину графа
 */
public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        ArrayList<Integer> g[] = new ArrayList[n];
        int pos = Integer.parseInt(reader.readLine());

        for (int i = 0; i < n; i++) g[i] = new ArrayList<Integer>();

        dfs(pos, g);
    }

    public static void dfs(int pos, ArrayList<Integer> g[]) {
        boolean[] used = new boolean[g.length];
        used[pos] = true;

        System.out.println(pos);
        for (int next : g[pos]) {
            if (!used[next]) dfs(next, g);
        }
    }
}





