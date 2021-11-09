package com.company.leetcode.intersection_of_two_arrays_ii;

import java.util.*;

class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> countMap = new HashMap<>();

        for (int n : nums1) {
            countMap.put(n, countMap.getOrDefault(n, 0) + 1);
        }

        List<Integer> list = new ArrayList<>();
        for (int n : nums2) {
            if (countMap.containsKey(n) && countMap.get(n) != 0) {
                countMap.put(n, countMap.get(n) - 1);
                list.add(n);
            }
        }

        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }

        return result;
    }
}
