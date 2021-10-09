package com.company.leetcode.strStr;

class Solution28_2 {
    public int strStr(String haystack, String needle) {
        if (needle.equals("")) {
            return 0;
        }

        int[] table = makeTable(needle);
        int targetLen = haystack.length();
        int patternLen = needle.length();

        int j = 0;
        for (int i = 0; i < targetLen; i++) {
            while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                j = table[j - 1];
            }

            if (haystack.charAt(i) == needle.charAt(j)) {
                if (j == patternLen - 1) {
                    return i - patternLen + 1;
                } else {
                    j++;
                }
            }
        }

        return -1;
    }

    public int[] makeTable(String pattern) {
        int[] table = new int[pattern.length()];

        int j = 0;
        for (int i = 1; i < pattern.length(); i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = table[j - 1];
            }

            if (pattern.charAt(i) == pattern.charAt(j)) {
                table[i] = ++j;
            }
        }

        return table;
    }
}
