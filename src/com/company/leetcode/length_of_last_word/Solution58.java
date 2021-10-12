package com.company.leetcode.length_of_last_word;

public class Solution58 {
    public int lengthOfLastWord(String s) {
        String[] sSplit = s.split("( )+");

        return sSplit[sSplit.length - 1].length();
    }
}
