package com.company.leetcode.pascals_triangle;

import java.util.*;

class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> pascals = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            pascals.add(new ArrayList<>());
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    pascals.get(i).add(1);
                } else {
                    int left = pascals.get(i - 1).get(j - 1);
                    int right = pascals.get(i - 1).get(j);
                    pascals.get(i).add(left + right);
                }
            }
        }

        return pascals;
    }
}
