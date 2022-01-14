package com.company.leetcode.happy_number;

import java.util.*;

class Solution {
    Set<Integer> set = new HashSet<>();

    public boolean isHappy(int n) {
        while (!set.contains(n) && n != 1) {
            List<Integer> digits = makeDigits(n);
            set.add(n);
            n = makeNextN(digits);
        }

        return n == 1;
    }

    public int makeNextN(List<Integer> digits) {
        int result = 0;
        for (int digit : digits) {
            result += (int) Math.pow(digit, 2);
        }

        return result;
    }

    public List<Integer> makeDigits(int n) {
        List<Integer> digits = new ArrayList<>();
        while (n / 10 != 0) {
            digits.add(n % 10);
            n /= 10;
        }
        digits.add(n);

        return digits;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isHappy(7));
    }
}
