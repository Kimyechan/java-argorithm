package com.company.leetcode.length_of_last_word;

public class Solution58_2 {
    public int lengthOfLastWord(String s) {
        int idx = s.length() - 1;
        while (idx >= 0) {
            if (s.charAt(idx) == ' ') {
                idx--;
            } else {
                break;
            }
        }

        int lastLen = 0;
        while (idx >= 0) {
            if (s.charAt(idx) != ' ') {
                lastLen += 1;
                idx -= 1;
            } else {
                break;
            }
        }

        return lastLen;
    }
}
