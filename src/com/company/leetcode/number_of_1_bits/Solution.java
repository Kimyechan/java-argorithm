package com.company.leetcode.number_of_1_bits;

public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & 1) == 1) {
                count = count + 1;
            }
            n = n >> 1;
        }

        return count;
    }
}