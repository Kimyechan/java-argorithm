package com.company.programmers.kakao2022.s1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {
    Map<String, Set<String>> giveMap = new HashMap<>();
    Map<String, Set<String>> receiveMap = new HashMap<>();

    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = {};

        for (String r : report) {
            String[] rSplit = r.split(" ");
            String give = rSplit[0];
            String receive = rSplit[1];

            if (giveMap.containsKey(give)) {
                giveMap.get(give).add(receive);
            } else {
                Set<String> set = new HashSet<>();
                set.add(receive);
                giveMap.put(give, set);
            }

            if (receiveMap.containsKey(receive)) {
                receiveMap.get(receive).add(give);
            } else {
                Set<String> set = new HashSet<>();
                set.add(give);
                receiveMap.put(receive, set);
            }
        }

        answer = new int[id_list.length];
        for (int i = 0; i < id_list.length; i++) {
            int count = 0;
            String id = id_list[i];
            for (String g : giveMap.getOrDefault(id, new HashSet<>())) {
                if (receiveMap.getOrDefault(g, new HashSet<>()).size() >= k) {
                    count++;
                }
            }
            answer[i] = count;
        }

        return answer;
    }
}














