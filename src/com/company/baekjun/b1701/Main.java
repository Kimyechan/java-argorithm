package com.company.baekjun.b1701;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();

        int answer = solution(s);

        System.out.println(answer);
    }

    private static int solution(String s) {
        int sLen = s.length();

        int maxLen = 0;
        for (int i = 0; i < sLen; i++) {
            String sFragment = s.substring(i, sLen);
            maxLen = Math.max(maxLen, calcMaxLen(sFragment));
        }

        return maxLen;
    }

    private static int calcMaxLen(String s) {
        int len = s.length();
        int[] pi = new int[len];
        int j = 0;

        int maxLen = 0;
        for (int i = 1; i < len; i++) { // i = 1
            while (j > 0 && s.charAt(i) != s.charAt(j)) {
                j = pi[j - 1];
            }
            if (s.charAt(i) == s.charAt(j)) {
                pi[i] = ++j;
                maxLen = Math.max(maxLen, pi[i]);
            }
        }

        return maxLen;
    }
}
