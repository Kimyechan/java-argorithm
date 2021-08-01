package com.company.baekjun.b1701;

public class KMP3 {
    public static int[] makeTable(String pattern) {
        int patternLen = pattern.length();
        int[] pi = new int[patternLen];

        int j = 0;
        for (int i = 1; i < patternLen; i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = pi[j - 1];
            }

            if (pattern.charAt(i) == pattern.charAt(j)) {
                pi[i] = ++j;
            }
        }

        return pi;
    }

    public static void kmp(String target, String pattern) {
        int[] pi = makeTable(pattern);
        int targetLen = target.length();
        int patterLenLen = pattern.length();

        int j = 0;
        for (int i = 0; i < targetLen; i++) {
            while (j > 0 && target.charAt(i) != pattern.charAt(j)) {
                j = pi[j - 1];
            }

            if (target.charAt(i) == pattern.charAt(j)) {
                if (j == patterLenLen - 1) {
                    System.out.printf("%d번째 인덱스에서 발견!!", i - patterLenLen + 1);
                } else {
                    j++;
                }
            }
        }
    }

    public static void main(String[] args) {
        kmp("abcdabcabb", "abcab");
    }
}
