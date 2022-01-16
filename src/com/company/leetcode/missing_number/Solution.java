package com.company.leetcode.missing_number;

class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int[] existed = new int[n + 1];

        for (int num : nums) {
            existed[num] = 1;
        }

        for (int i = 0; i < n + 1; i++) {
            if (existed[i] != 1) {
                return i;
            }
        }

        return -1;
    }
}