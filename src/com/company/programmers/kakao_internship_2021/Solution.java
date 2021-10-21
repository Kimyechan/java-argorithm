package com.company.programmers.kakao_internship_2021;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    Map<String, Integer> numberMap = new HashMap<>();

    public int solution(String s) {
        initNumberMap();

        StringBuilder result = new StringBuilder();
        StringBuilder number = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                result.append(s.charAt(i));
            } else {
                number.append(s.charAt(i));
                if (numberMap.containsKey(number.toString())) {
                    result.append(numberMap.get(number.toString()));
                    number = new StringBuilder();
                }
            }
        }

        return Integer.parseInt(result.toString());
    }

    private void initNumberMap() {
        numberMap.put("zero", 0);
        numberMap.put("one", 1);
        numberMap.put("two", 2);
        numberMap.put("three", 3);
        numberMap.put("four", 4);
        numberMap.put("five", 5);
        numberMap.put("six", 6);
        numberMap.put("seven", 7);
        numberMap.put("eight", 8);
        numberMap.put("nine", 9);
    }
}
