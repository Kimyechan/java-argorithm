package com.company.leetcode.roman_to_integer;

import java.util.HashMap;
import java.util.Map;

public class RomanToInt {
    Map<Character, Integer> romanMap = new HashMap<>();

    public int romanToInt(String s) {
        initRomanMap();

        int number = 0;
        int idx = 0;
        while (idx < s.length() - 1) {
            int first = romanMap.get(s.charAt(idx));
            int second = romanMap.get(s.charAt(idx + 1));

            if (first >= second) {
                number += first;
                idx += 1;
            } else {
                number += second - first;
                idx += 2;
            }
        }

        if (idx < s.length()) {
            number += romanMap.get(s.charAt(s.length() - 1));
        }

        return number;
    }

    private void initRomanMap() {
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);
    }
}
