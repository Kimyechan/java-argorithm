package com.company.leetcode.three_sum;

import java.util.*;

public class Solution2 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        int len = nums.length;

        Arrays.sort(nums);

        for (int i = 0; i < len - 2; i++) {
            if (i > 0 && (nums[i] == nums[i - 1])) {
                continue;
            }

            int left = i + 1;
            int right = len - 1;
            while (left < right) {
                int sum = nums[left] + nums[i] + nums[right];

                if (sum == 0) {
                    result.add(Arrays.asList(nums[left], nums[i], nums[right]));
                    left++;
                    right--;
                    while (left < right && nums[left] == nums[left - 1]) left++;
                    while (left < right && nums[right] == nums[right + 1]) right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return result;
    }
}
