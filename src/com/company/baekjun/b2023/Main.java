package com.company.baekjun.b2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    static List<Integer> strangePrimeList = new ArrayList<>();
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        dfs(0, 0);

        Collections.sort(strangePrimeList);
        for (Integer strangePrime : strangePrimeList) {
            System.out.println(strangePrime);
        }
    }

    private static void dfs(int num, int len) {
        if (len == n) {
            strangePrimeList.add(num);
            return;
        }

        for (int i = 0; i < 10; i++) {
            int nextNum = num * 10 + i;
            if (checkPrime(nextNum)) {
                dfs(nextNum, len + 1);
            }
        }
    }

    private static boolean checkPrime(int num) {
        if (num == 0 || num == 1) {
            return false;
        }

        if (num == 2) {
            return true;
        }

        for (int i = 2; i < Math.sqrt(num) + 1; i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }
}