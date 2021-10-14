package com.company.leetcode.plus_one;

public class Solution {
    public int[] plusOne(int[] digits) {
        int len = digits.length;

        int up = 0;
        if (digits[len - 1] + 1 == 10) {
            up = 1;
            digits[len - 1] = 0;
        } else {
            digits[len - 1] += 1;
        }

        for (int i = len - 2; i >= 0; i--) {
            if (up == 0) {
                break;
            }

            int sum = digits[i] + up;
            if (sum >= 10) {
                digits[i] = 0;
                up = 1;
            } else {
                digits[i] = sum;
                up = 0;
            }
        }

        int[] result = new int[len + 1];
        if (up == 1) {
            result[0] = 1;
            for (int i = 1; i < len + 1; i++) {
                result[i] = digits[i - 1];
            }
            return result;
        } else {
            return digits;
        }
    }
}
