package com.company.leetcode.sqrtx;

public class Solution {
    public int mySqrt(int x) {
        int number = 1;

        while (number <= x / number) {
            number += 1;
        }

        return number - 1;
    }
}
