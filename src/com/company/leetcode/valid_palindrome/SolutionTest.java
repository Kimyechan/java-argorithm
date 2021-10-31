package com.company.leetcode.valid_palindrome;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @Test
    @DisplayName("알파벳 만든 뒤 펠린드롬")
    public void isPalindrome() {
        String s = "A man, a plan, a canal: Panama";

        Solution solution = new Solution();
        boolean result = solution.isPalindrome(s);

        assertTrue(result);
    }

    @Test
    @DisplayName("알파벳 만든 뒤 펠린드롬")
    public void isPalindrome2() {
        String s = "race a car";

        Solution2 solution = new Solution2();
        boolean result = solution.isPalindrome(s);

        assertFalse(result);
    }
}