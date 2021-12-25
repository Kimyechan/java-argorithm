package com.company.leetcode.longest_substring_without_repeating_characters;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @Test
    public void solution() {
        String s = "abba";

        Solution solution = new Solution();
        int result = solution.lengthOfLongestSubstring(s);

        assertEquals(result, 3);
    }
}