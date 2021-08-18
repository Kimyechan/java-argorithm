package com.company.programmers.kakao2018.뉴스클러스터링;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    Map<String, Integer> map1 = new HashMap<>();
    Map<String, Integer> map2 = new HashMap<>();

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution("E=M*C^2", "e=m*c^2"));
    }

    public int solution(String str1, String str2) {
        int answer = 0;
        for(int i = 0; i < str1.length() - 1; i++) {
            String subStr1 = str1.substring(i, i + 2);

            if (subStr1.matches("[a-z|A-Z]*")) {
                map1.put(subStr1.toLowerCase(), map1.getOrDefault(subStr1.toLowerCase(), 0) + 1);
            }
        }

        for(int i = 0; i < str2.length() - 1; i++) {
            String subStr2 = str2.substring(i, i + 2);

            if (subStr2.matches("[a-z|A-Z]*")) {
                map2.put(subStr2.toLowerCase(), map2.getOrDefault(subStr2.toLowerCase(), 0) + 1);
            }
        }

        int interSetCount = calcInterSetCount();
        int unionSetCount = calcUnionSetCount();

        if (map1.size() == 0 && map2.size() == 0) {
            answer = 65536;
        } else {
            answer = (int) (interSetCount * 65536.0 / unionSetCount);
        }
        return answer;
    }

    private int calcUnionSetCount() {
        int unionSetCount = 0;
        for (String s : map1.keySet()) {
            if (map2.containsKey(s)) {
                unionSetCount += map1.get(s) > map2.get(s) ? map1.get(s) : map2.get(s);
            } else {
                unionSetCount += map1.get(s);
            }
        }

        for (String s : map2.keySet()) {
            if (map1.containsKey(s)) {
                continue;
            }
            unionSetCount += map2.get(s);
        }
        return unionSetCount;
    }

    private int calcInterSetCount() {
        int interSetCount = 0;
        for (String s : map1.keySet()) {
            if (map2.containsKey(s)) {
                interSetCount += map1.get(s) < map2.get(s) ? map1.get(s) : map2.get(s);
            }
        }
        return interSetCount;
    }
}