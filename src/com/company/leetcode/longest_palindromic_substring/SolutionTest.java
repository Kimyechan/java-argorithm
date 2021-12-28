package com.company.leetcode.longest_palindromic_substring;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @Test
    public void solution() {
        String s = "aaaa";

        Solution2 solution2 = new Solution2();
        String result = solution2.longestPalindrome(s);

        assertEquals(result, "aaaa");
    }
}