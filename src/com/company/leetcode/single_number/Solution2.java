package com.company.leetcode.single_number;


import java.util.Arrays;

public class Solution2 {
    public int singleNumber(int[] nums) {
        Arrays.sort(nums);

        int idx = 0;
        while (idx < nums.length - 1) {
            if (nums[idx] == nums[idx + 1]) {
                idx += 2;
            } else {
                break;
            }
        }

        return nums[idx];
    }
}
