package com.company.leetcode.search_index_position;

public class Solution35 {
    public int searchInsert(int[] nums, int target) {
        int start = 0;
        int end = nums.length;

        while (start < end) {
            int mid = (start + end) / 2;

            if (nums[mid] >= target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return start;
    }
}
