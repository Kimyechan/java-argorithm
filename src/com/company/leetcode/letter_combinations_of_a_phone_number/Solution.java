package com.company.leetcode.letter_combinations_of_a_phone_number;

import java.util.*;

class Solution {
    Map<Character, String> digitMap = new HashMap<>();
    List<String> result = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        initMap();

        combination(0, digits, "");

        return result;
    }

    public void initMap() {
        digitMap.put('2', "abc");
        digitMap.put('3', "def");
        digitMap.put('4', "ghi");
        digitMap.put('5', "jkl");
        digitMap.put('6', "mno");
        digitMap.put('7', "pqrs");
        digitMap.put('8', "tuv");
        digitMap.put('9', "wxyz");
    }

    public void combination(int idx, String digits, String combi) {
        if (digits.length() == 0) {
            return;
        }

        if (digits.length() == combi.length()) {
            result.add(combi);
            return;
        }

        String possibleLetter = digitMap.get(digits.charAt(idx));
        for (Character letter : possibleLetter.toCharArray()) {
            String newCombi = combi + letter;
            combination(idx + 1, digits, newCombi);
        }
    }
}
