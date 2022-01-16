package com.company.leetcode.missing_number;

public class Solution2 {
    public int missingNumber(int[] nums) {
        int result = nums.length;
        for (int i = 0; i < nums.length; i++) {
            result ^= i;
            result ^= nums[i];
        }

        return result;
    }
}
