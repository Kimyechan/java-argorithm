package com.company.baekjun.b2661;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2 {
    static String result = "";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        recursion(n,"");

        System.out.println(result);
    }

    private static void recursion(int n, String number) {
        if (number.length() == n) {
            result = number;
            return;
        }

        for (int i = 1; i <= 3; i++) {
            if (!result.equals("")) {
                return;
            }

            StringBuilder sb = new StringBuilder();
            sb.append(number).append(i);
            if (checkGoodNumber(sb.toString())) {
                recursion(n, sb.toString());
            }
        }
    }

    private static boolean checkGoodNumber(String number) {
        for (int i = 1; i < number.length() / 2 + 1; i++) {
            for (int j = 0; j < number.length() - 2 * i + 1; j++) {
                if (number.substring(j, j + i).equals(number.substring(j + i, j + 2 * i))) {
                    return false;
                }
            }
        }
        return true;
    }
}