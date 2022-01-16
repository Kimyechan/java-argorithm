package com.company.leetcode.missing_number;

public class Solution3 {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int result = n * (n + 1) / 2;
        for (int i = 0; i < nums.length; i++) {
            result -= nums[i];
        }

        return result;
    }
}
