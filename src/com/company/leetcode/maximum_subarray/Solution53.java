package com.company.leetcode.maximum_subarray;

public class Solution53 {
    public int maxSubArray(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }

        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            maxSum = Math.max(maxSum, nums[i]);
            for (int j = 0; j < i; j++) {
                maxSum = Math.max(maxSum, nums[i] - nums[j]);
            }
        }

        return maxSum;
    }
}
