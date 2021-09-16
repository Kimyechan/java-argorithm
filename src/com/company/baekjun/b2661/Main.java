package com.company.baekjun.b2661;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static String result = "";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        recursion(n, new StringBuilder());

        System.out.println(result);
    }

    private static void recursion(int n, StringBuilder sb) {
        if (sb.toString().length() == n) {
            result = sb.toString();
            return;
        }

        for (int i = 1; i <= 3; i++) {
            if (!result.equals("")) {
                return;
            }

            sb.append(i);
            if (checkGoodNumbers(sb.toString())) {
                recursion(n, sb);
            }
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    private static boolean checkGoodNumbers(String newS) {
        int len = newS.length();
        for (int i = 1; i < len / 2 + 1; i++) {
            for (int j = 0; j < len - 2 * i + 1; j++) {
                if (newS.substring(j, j + i).equals(newS.substring(j + i, j + 2 * i))) {
                    return false;
                }
            }
        }
        return true;
    }
}