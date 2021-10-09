package com.company.leetcode.strStr;

public class Solution28 {
    public int strStr(String haystack, String needle) {
        if (haystack.equals("") && needle.equals("")) {
            return 0;
        }

        loop:
        for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {
            for (int j = 0; j < needle.length(); j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    continue loop;
                }
            }
            return i;
        }

        return -1;
    }
}
