package com.company.leetcode.reverse;

class Solution2 {
    public int reverse(int x) {
        long reverseNumber = 0;

        long digit = 1;
        while (x / digit != 0) {
            digit *= 10;
        }
        digit /= 10;

        while (digit != 0) {
            reverseNumber += x % 10 * digit;
            digit /= 10;
            x /= 10;
        }

        if (reverseNumber > Integer.MAX_VALUE || reverseNumber < Integer.MIN_VALUE) {
            return 0;
        } else {
            return (int) reverseNumber;
        }
    }
}
