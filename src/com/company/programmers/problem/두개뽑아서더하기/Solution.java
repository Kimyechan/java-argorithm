package com.company.programmers.problem.두개뽑아서더하기;

import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = {};

        int len = numbers.length;
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                set.add(numbers[i] + numbers[j]);
            }
        }

        List<Integer> list = new ArrayList<>(set);
        Collections.sort(list);

        answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }
}