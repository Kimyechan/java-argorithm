package com.company.leetcode.Remove_Duplicates_from_Sorted_Array;

public class Solution26 {
    public int removeDuplicates(int[] nums) {
        int cur = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] != nums[i + 1]) {
                nums[cur++] = nums[i];
            }
        }
        if (nums.length != 0) {
            nums[cur++] = nums[nums.length - 1];
        }

        return cur;
    }
}
