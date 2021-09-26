package com.company.baekjun.b2661;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main3 {
    static String result = "";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        dfs(n, "");

        System.out.println(result);
    }

    private static void dfs(int n, String number) {
        if (!result.equals("")) {
            return;
        }

        if (number.length() == n) {
            result = number;
            return;
        }

        for (int i = 1; i <= 3; i++) {
            String newNumber = number + i;
            if (checkGoodNumber(newNumber)) {
                dfs(n, newNumber);
            }
        }
    }

    private static boolean checkGoodNumber(String number) {
        int len = number.length();

        for (int i = 1; i < len / 2 + 1; i++) {
            if (number.substring(len - i, len).equals(number.substring(len - 2 * i, len - i))) {
                return false;
            }
        }

        return true;
    }
}