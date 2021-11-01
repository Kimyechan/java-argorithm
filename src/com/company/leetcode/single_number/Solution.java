package com.company.leetcode.single_number;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int result = 0;
        for (Integer num : map.keySet()) {
            if (map.get(num) == 1) {
                result = num;
            }
        }

        return result;
    }
}
