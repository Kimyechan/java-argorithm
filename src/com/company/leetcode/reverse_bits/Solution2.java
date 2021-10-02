package com.company.leetcode.reverse_bits;

public class Solution2 {
    public class Solution {
        // you need treat n as an unsigned value
        public int reverseBits(int n) {
            int num = 0;
            for (int i = 0; i < 32; i++) {
                num = num << 1;
                num |= (n & 1);
                n = n >> 1;
            }

            return num;
        }
    }
}
