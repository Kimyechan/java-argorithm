package com.company.programmers.weekly.week5;

import java.util.*;

class Solution {
    Set<String> set = new HashSet<>();
    Map<String, Integer> map = new HashMap<>();

    public int solution(String word) {
        List<String> list = List.of("", "A", "E", "I", "O", "U");

        concatAEIOU(0, "", list);
        List<String> words = new ArrayList<>(set);

        for (int i = 0; i < words.size(); i++) {
            map.put(words.get(i), i);
        }

        return map.get(word);
    }

    public void concatAEIOU(int count, String w, List<String> list) {
        if (count == 5) {
            set.add(w);
            return;
        }

        for (String a : list) {
            String newW = w + a;
            concatAEIOU(count + 1, newW, list);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution("AAAAE"));
    }
}
