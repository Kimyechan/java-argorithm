package com.company.leetcode.longest_common_prefix;

public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        int minLen = Integer.MAX_VALUE;
        for (String str : strs) {
            minLen = Math.min(minLen, str.length());
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < minLen; i++) {
            int mask = 1 << (strs[0].charAt(i) - 'a');
            boolean maskCheck = true;
            for (int j = 1; j < strs.length; j++) {
                if ((mask & (1 << (strs[j].charAt(i)) - 'a')) != mask) {
                    maskCheck = false;
                    break;
                }
            }
            if (maskCheck) {
                sb.append(strs[0].charAt(i));
            } else {
                break;
            }
        }

        return sb.toString();
    }
}