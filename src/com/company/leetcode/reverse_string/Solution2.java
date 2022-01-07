package com.company.leetcode.reverse_string;

class Solution2 {
    public void reverseString(char[] s) {
        recursion(s, 0);
    }

    public void recursion(char[] s, int idx) {
        if (idx == s.length / 2) {
            return;
        }

        recursion(s, idx + 1);
        char temp = s[idx];
        s[idx] = s[s.length - 1 - idx];
        s[s.length - 1 - idx] = temp;
    }
}
