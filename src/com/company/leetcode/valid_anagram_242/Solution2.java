package com.company.leetcode.valid_anagram_242;
import java.util.*;

class Solution2 {
    public boolean isAnagram(String s, String t) {
        int[] alphabets = new int[26];

        for (int i = 0; i < s.length(); i++) {
            alphabets[s.charAt(i) - 'a'] += 1;
        }

        for (int i = 0; i < t.length(); i++) {
            alphabets[t.charAt(i) - 'a'] -= 1;
        }

        for (int i = 0; i < 26; i++) {
            if (alphabets[i] != 0) {
                return false;
            }
        }

        return true;
    }
}
