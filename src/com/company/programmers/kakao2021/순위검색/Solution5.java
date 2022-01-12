package com.company.programmers.kakao2021.순위검색;

import java.util.*;

class Solution5 {
    Map<String, List<Integer>> scoreMap = new HashMap<>();

    public int[] solution(String[] info, String[] query) {
        for (String inf : info) {
            String[] selection = inf.split(" ");
            putInfoToMap(selection, 0, "");
        }

        for (List<Integer> scoreList : scoreMap.values()) {
            Collections.sort(scoreList);
        }

        int[] answer = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            answer[i] = search(query[i]);
        }

        return answer;
    }

    public int search(String query) {
        String newQuery = query.replaceAll(" and ", " ");
        StringBuilder condition = new StringBuilder();
        String[] q = newQuery.split(" ");
        for (int i = 0; i < 4; i++) {
            condition.append(q[i]);
        }

        List<Integer> scoreList = scoreMap.getOrDefault(condition.toString(), new ArrayList<>());

        int start = 0;
        int end = scoreList.size();

        Integer score = Integer.parseInt(q[4]);
        while (start < end) {
            int mid = (start + end) / 2;

            if (scoreList.get(mid) >= score) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return scoreList.size() - end;
    }

    public void putInfoToMap(String[] selection, int idx, String condition) {
        if (idx == 4) {
            Integer score = Integer.parseInt(selection[4]);
            if (scoreMap.containsKey(condition)) {
                scoreMap.get(condition).add(score);
            } else {
                List<Integer> scoreList = new ArrayList<>();
                scoreList.add(score);
                scoreMap.put(condition, scoreList);
            }

            return;
        }

        putInfoToMap(selection, idx + 1, condition + selection[idx]);
        putInfoToMap(selection, idx + 1, condition + "-");
    }
}