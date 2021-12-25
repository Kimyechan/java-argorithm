package com.company.leetcode.longest_substring_without_repeating_characters;

import java.util.HashMap;
import java.util.Map;

public class Solution2 {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }

        Map<Character, Integer> map = new HashMap<>();
        int max = 0;
        int start = 0;
        for (int end = 0; end < s.length(); end++) {
            char c = s.charAt(end);
            if (map.containsKey(c)) {
                start = Math.max(start, map.get(c) + 1);
            }

            max = Math.max(max, end - start + 1);
            map.put(c, end);
        }

        return max;
    }
}
