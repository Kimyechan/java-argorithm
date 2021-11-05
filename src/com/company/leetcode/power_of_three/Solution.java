package com.company.leetcode.power_of_three;

class Solution {
    public boolean isPowerOfThree(int n) {
        double three = 3.0;

        double result = n;
        while (result >= 1.0) {
            if (result == 1.0) {
                return true;
            }
            result /= three;
        }

        return false;
    }
}
