package com.company.programmers.kakao2018.뉴스클러스터링;

import java.util.HashMap;
import java.util.Map;

public class Solution2 {
    static Map<String, Integer> map1;
    static Map<String, Integer> map2;

    public int solution(String str1, String str2) {
        int answer = 0;

        map1 = createMap(str1.toLowerCase());
        map2 = createMap(str2.toLowerCase());

        int interSetSize = countInterSetSize();
        int unionSetSize = countUnionSetSize();

        if (unionSetSize == 0) {
            answer = 65536;
        } else {
            answer = (int) (((double) interSetSize) / unionSetSize * 65536);
        }

        return answer;
    }

    private int countUnionSetSize() {
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

    private int countInterSetSize() {
        int interSetCount = 0;
        for (String s : map1.keySet()) {
            if (map2.containsKey(s)) {
                interSetCount += map1.get(s) < map2.get(s) ? map1.get(s) : map2.get(s);
            }
        }
        return interSetCount;
    }

    private Map<String, Integer> createMap(String str) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length() - 1; i++) {
            String twoWord = str.substring(i, i + 2);
            if (twoWord.matches("[a-z|A-Z]*")) {
                map.put(twoWord, map.getOrDefault(twoWord, 0) + 1);
            }
        }

        return map;
    }
}