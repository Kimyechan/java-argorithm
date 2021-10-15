package com.company.programmers.kakao2019_winter_intership.s2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Solution {
    public int[] solution(String s) {
        int[] answer = {};
        s = s.substring(2, s.length() - 2);

        String[] setString = s.split("},\\{");
        Arrays.sort(setString, Comparator.comparingInt(String::length));

        List<Integer> tuple = new ArrayList<>();
        for (String set : setString) {
            String[] elements = set.split(",");
            for (String element : elements) {
                int number = Integer.parseInt(element);
                if (!tuple.contains(number)) {
                    tuple.add(number);
                }
            }
        }

        answer = new int[tuple.size()];
        for (int i = 0; i < tuple.size(); i++) {
            answer[i] = tuple.get(i);
        }
        return answer;
    }
}
