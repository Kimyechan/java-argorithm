package com.company.leetcode.pow;

import java.util.*;

class Solution {
    Map<Integer, Double> caches = new HashMap<>();

    public double myPow(double x, int n) {
        double num = n >= 0 ? x : 1 / x;
        return recursion(num, n);
    }

    public double recursion(double num, int n) {
        if (n == 1) {
            return num;
        }

        if (n == 0) {
            return 1;
        }

        double result = 0;
        if (caches.containsKey(n / 2)) {
            result = caches.get(n / 2);
        } else {
            result = recursion(num, n / 2);
            caches.put(n / 2, result);
        }

        if (n % 2 == 0) {
            return result * result;
        } else {
            return result * result * num;
        }
    }
}
