package com.company.leetcode.fizz_buzz;

import java.util.*;

class Solution {
    public List<String> fizzBuzz(int n) {
        List<String> fbList = new ArrayList<String>();

        for (int i = 1; i <= n; i++) {
            if (i % 15 == 0) {
                fbList.add("FizzBuzz");
            } else if (i % 3 == 0) {
                fbList.add("Fizz");
            } else if (i % 5 == 0) {
                fbList.add("Buzz");
            } else {
                fbList.add(String.valueOf(i));
            }
        }

        return fbList;
    }
}
