package com.company.baekjun.b1701;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int maxSubLen = 0;
        String s = br.readLine();
        for (int i = 0; i < s.length(); i++) {
            int sFragmentLen = calcSFragmentLen(s.substring(i));
            maxSubLen = Math.max(maxSubLen, sFragmentLen);
        }

        System.out.println(maxSubLen);
    }

    private static int calcSFragmentLen(String sFragment) {
        int[] pi = new int[sFragment.length()];
        int maxLen = 0;
        int j = 0;

        for (int i = 1; i < sFragment.length(); i++) {
            while (j > 0 && sFragment.charAt(i) != sFragment.charAt(j)) {
                j = pi[j - 1];
            }

            if (sFragment.charAt(i) == sFragment.charAt(j)) {
                pi[i] = ++j;
                maxLen = Math.max(maxLen, pi[i]);
            }
        }

        return maxLen;
    }
}
