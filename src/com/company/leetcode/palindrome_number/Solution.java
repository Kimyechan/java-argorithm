package com.company.leetcode.palindrome_number;

class Solution {
    public boolean isPalindrome(int x) {
        String s = String.valueOf(x);
        boolean result = true;

        int loop = 0;
        if (s.length() % 2 == 0) {
            loop = s.length() / 2;
        } else {
            loop = s.length() / 2 + 1;
        }

        for (int i = 0; i < loop; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                result = false;
            }
        }

        return result;
    }
}
