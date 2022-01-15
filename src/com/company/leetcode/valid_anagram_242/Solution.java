package com.company.leetcode.valid_anagram_242;

import java.util.*;

class Solution {
    Map<Character, Integer> map = new HashMap<>();

    public boolean isAnagram(String s, String t) {
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }

        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (!map.containsKey(c)) {
                return false;
            }

            if (map.get(c) == 0) {
                return false;
            }

            map.put(c, map.get(c) - 1);
            if (map.get(c) == 0) {
                map.remove(c);
            }
        }

        for (Integer remain : map.values()) {
            if (remain != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isAnagram("anagram", "nagaram"));
    }
}
