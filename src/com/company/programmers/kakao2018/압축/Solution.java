package com.company.programmers.kakao2018.압축;

import java.util.*;

public class Solution {
    Map<String, Integer> indexMap = new HashMap<>();

    public int[] solution(String msg) {
        int[] answer = {};
        for (char c = 'A'; c <= 'Z'; c++) {
            indexMap.put(String.valueOf(c), c - 'A' + 1);
        }

        List<Integer> result = new ArrayList<>();

        int value = 27;
        int start = 0;
        while (start < msg.length()) {
            int end = msg.length();
            while (end > start) {
                String word = msg.substring(start, end);
                if (indexMap.containsKey(word)) {
                    result.add(indexMap.get(word));
                    if (end != msg.length()) {
                        indexMap.put(word + msg.charAt(end), value++);
                    }
                    break;
                }
                end--;
            }
            start = end;
        }

        answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.solution("KAKAO")));
    }
}