package com.company.leetcode.first_unique_character_in_a_string;

class Solution {
    public int firstUniqChar(String s) {
        int[] counts = new int[26];

        for (int i = 0; i < s.length(); i++) {
            int pos = s.charAt(i) - 'a';
            counts[pos] += 1;
        }

        for (int i = 0; i < s.length(); i++) {
            int pos = s.charAt(i) - 'a';
            if (counts[pos] == 1) {
                return i;
            }
        }

        return -1;
    }
}