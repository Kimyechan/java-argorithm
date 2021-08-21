package com.company.programmers.kakao2018.캐시;

import java.util.LinkedList;
import java.util.List;

public class Solution {
    static List<String> cache = new LinkedList<>();

    public int solution(int cacheSize, String[] cities) {
        int answer = 0;

        int time = 0;
        for (String city : cities) {
            String cityLower = city.toLowerCase();

            if (cacheSize == 0) {
                time += 5;
                continue;
            }

            if (cache.contains(cityLower)) {
                cache.remove(cityLower);
                cache.add(cityLower);
                time += 1;
            } else {
                if (cache.size() >= cacheSize) {
                    cache.remove(0);
                }
                cache.add(cityLower);
                time += 5;
            }
        }

        answer = time;

        return answer;
    }
}