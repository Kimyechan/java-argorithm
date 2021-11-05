package com.company.leetcode.power_of_three;

class Solution2 {
    public boolean isPowerOfThree(int n) {
        while (n != 0 && n % 3 == 0) {
            n /= 3;
        }

        return n == 1;
    }
}
