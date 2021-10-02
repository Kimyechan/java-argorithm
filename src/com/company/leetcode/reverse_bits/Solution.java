package com.company.leetcode.reverse_bits;

public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        long result = n;
        if (n < 0) {
            result = (long) Math.pow(2, 32) + n;
        }

        long reverseResult = 0;
        long tail = 0;
        for (int i = 0; i < 32; i++) {
            tail = result % 2;
            reverseResult = reverseResult * 2 + tail;
            result /= 2;
        }

        return (int) reverseResult;
    }
}