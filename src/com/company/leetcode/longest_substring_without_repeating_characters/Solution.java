package com.company.leetcode.longest_substring_without_repeating_characters;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }

        Map<Character, Integer> map = new HashMap<>();

        int max = 0;
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            char key = s.charAt(i);
            if (map.containsKey(key)) {
                j = Math.max(j, map.get(key) + 1);
            }
            map.put(key, i);
            max = Math.max(max, i - j + 1);
        }
        return max;
    }
}
