package com.company.leetcode.majority_element;

import java.util.*;

class Solution {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int maxCount = 0;
        int majorNum = 0;

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            if (maxCount < map.get(num)) {
                maxCount = map.get(num);
                majorNum = num;
            }
        }

        return majorNum;
    }
}
