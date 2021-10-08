package com.company.leetcode.Remove_Duplicates_from_Sorted_Array;

public class Solution26_2 {
    public int removeDuplicates(int[] nums) {
        int cur = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] != nums[i + 1]) {
                nums[cur] = nums[i + 1];
                cur += 1;
            }
        }

        return cur;
    }
}
