package com.company.baekjun.b1701;

class KMP2 {
    public static int[] makeKmpTable(String pattern) {
        int patternSize = pattern.length();
        int[] pi = new int[patternSize]; // index번째까지 문자열까지에서 접두사와 접미사의 일치 길이
        int j = 0;

        for (int i = 1; i < patternSize; i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = pi[j - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                pi[i] = ++j;
            }
        }
        return pi;
    }

    public static int kmp(String parent, String pattern) {
        int count = 0;

        int[] pi = makeKmpTable(pattern);
        int parentSize = parent.length();
        int j = 0;

        for (int i = 0; i < parentSize; i++) {
            while (j > 0 && parent.charAt(i) != pattern.charAt(j)) {
                j = pi[j - 1];
            }
            if (parent.charAt(i) == pattern.charAt(j)) {
                if (j == pattern.length() - 1) {
                    count += 1;
                } else {
                    j += 1;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int result = kmp("abcdabcabb", "abc");

        System.out.println(result);
    }
}
