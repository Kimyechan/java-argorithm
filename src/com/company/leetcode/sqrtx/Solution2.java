package com.company.leetcode.sqrtx;

public class Solution2 {
    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        } else if (x == 1) {
            return 1;
        }

        long start = 1;
        long end = x;

        while (start < end) {
            long mid = (start + end) / 2;

            if (mid * mid > x) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return (int) start - 1;
    }
}
