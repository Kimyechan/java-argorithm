package com.company.programmers.kakao2018.캐시;

import java.util.ArrayList;
import java.util.List;

public class Solution2 {
    List<String> cacheList = new ArrayList<>();

    public int solution(int cacheSize, String[] cities) {
        int answer = 0; // 총 시간
        for (String city : cities) {
            city = city.toLowerCase();

            if (cacheSize == 0) {
                answer += 5;
                continue;
            }

            if (cacheList.contains(city)) {
                cacheList.remove(city);
                cacheList.add(city);
                answer += 1;
            } else {
                if (cacheList.size() >= cacheSize) {
                    cacheList.remove(0);
                }
                cacheList.add(city);
                answer += 5;
            }
        }

        return answer;
    }
}