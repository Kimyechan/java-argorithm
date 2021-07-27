package com.company.baekjun.b1701;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int answer = 0;
        String pattern = br.readLine();
        for (int i = 0; i < pattern.length(); i++) {
            int len = findMaxSubStringLen(pattern.substring(i));
            answer = Math.max(answer, len);
        }

        System.out.println(answer);
    }

    private static int findMaxSubStringLen(String pattern) {
        int maxLen = 0;
        int patternSize = pattern.length();
        int[] pi = new int[patternSize];
        int j = 0;

        for (int i = 1; i < patternSize; i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = pi[j - 1];
            }

            if (pattern.charAt(i) == pattern.charAt(j)) {
                pi[i] = ++j;
                maxLen = Math.max(maxLen, pi[i]);
            }
        }

        return maxLen;
    }
}

