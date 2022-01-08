package com.company.leetcode.fibonacci_number;

import java.util.*;

class Solution {
    Map<Integer, Integer> caches = new HashMap<>();

    public int fib(int n) {
        if (caches.containsKey(n)) {
            return caches.get(n);
        }

        int result = 0;
        if (n < 2) {
            result = n;
        } else {
            result = fib(n - 1) + fib(n - 2);
        }

        caches.put(n, result);

        return result;
    }
}