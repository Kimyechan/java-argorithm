package com.company.programmers.weekly.week5;

import java.util.*;

class Solution2 {
    List<String> words = new ArrayList<>();

    public int solution(String word) {
        List<String> list = List.of("A", "E", "I", "O", "U");

        concatAEIOU(0, "", list);

        return words.indexOf(word);
    }

    public void concatAEIOU(int count, String w, List<String> list) {
        if (count > 5) {
            return;
        }

        words.add(w);

        for (String a : list) {
            String newW = w + a;
            concatAEIOU(count + 1, newW, list);
        }
    }
}
