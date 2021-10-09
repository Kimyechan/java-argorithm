package com.company.leetcode.Remove_Element;

import java.util.ArrayList;
import java.util.List;

public class Solution27 {
    public int removeElement(int[] nums, int val) {
        List<Integer> aliveIdxs = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                aliveIdxs.add(i);
            }
        }

        for (int i = 0; i < aliveIdxs.size(); i++) {
            int idx = aliveIdxs.get(i);
            nums[i] = nums[idx];
        }

        return aliveIdxs.size();
    }
}
