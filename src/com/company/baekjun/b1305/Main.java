package com.company.baekjun.b1305;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int l;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        l = Integer.parseInt(br.readLine());
        String s = br.readLine();

        int minLen = findMinLen(s);

        System.out.println(minLen);
    }

    private static int findMinLen(String s) {
        int sLen = s.length();
        int[] pi = new int[sLen];
        int maxLen = 0;

        int j = 0;
        for (int i = 1; i < sLen; i++) {
            while (j > 0 && s.charAt(i) != s.charAt(j)) {
                j = pi[j - 1];
            }

            if (s.charAt(i) == s.charAt(j)) {
                pi[i] = ++j;
            }
        }

        return sLen - pi[sLen - 1];
    }
}