package com.company.programmers.kakao2018.캐시;

import java.util.ArrayList;
import java.util.List;

public class Solution3 {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;

        List<String> cacheList = new ArrayList<>();
        int time = 0;
        for (String city : cities) {
            String cityLower = city.toLowerCase();

            if (cacheSize == 0) {
                time += 5;
                continue;
            }

            if (cacheList.contains(cityLower)) {
                cacheList.remove(cityLower);
                cacheList.add(cityLower);
                time += 1;
            } else {
                if (cacheList.size() >= cacheSize) {
                    cacheList.remove(0);
                }
                cacheList.add(cityLower);
                time += 5;
            }
        }

        answer = time;

        return answer;
    }
}