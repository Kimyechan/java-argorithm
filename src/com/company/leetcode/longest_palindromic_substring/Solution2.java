package com.company.leetcode.longest_palindromic_substring;

public class Solution2 {
    public String longestPalindrome(String s) {
        String answer = null;
        int len = s.length();

        boolean[][] dp = new boolean[len][len];

        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1]);

                if (dp[i][j] && (answer == null || answer.length() < j - i + 1)) {
                    answer = s.substring(i, j + 1);
                }
            }
        }

        return answer;
    }
}
