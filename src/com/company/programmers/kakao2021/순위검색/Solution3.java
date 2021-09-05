package com.company.programmers.kakao2021.순위검색;

import java.util.*;

public class Solution3 {
    static Map<String, List<Integer>> scoreMap = new HashMap<>();

    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];

        for (int i = 0; i < info.length; i++) {
            String[] conditions = info[i].split(" ");
            createScoreMap(0, conditions, "");
        }

        for (Map.Entry<String, List<Integer>> entry : scoreMap.entrySet()) {
            List<Integer> scoreList = entry.getValue();
            Collections.sort(scoreList);

            scoreMap.put(entry.getKey(), scoreList);
        }

        for (int i = 0; i < query.length; i++) {
            query[i] = query[i].replaceAll(" and ", " ");
            String[] querySplits = query[i].split(" ");

            String conditionString = querySplits[0] + querySplits[1] + querySplits[2] + querySplits[3];
            int matchScore = Integer.parseInt(querySplits[4]);
            List<Integer> scoreList = scoreMap.getOrDefault(conditionString, new ArrayList<>());

            int matchCount = binarySearch(matchScore, scoreList);
            answer[i] = matchCount;
        }

        return answer;
    }

    private int binarySearch(int matchScore, List<Integer> scoreList) {
        int start = 0;
        int end = scoreList.size();

        while (start < end) {
            int mid = (start + end) /  2;

            if (scoreList.get(mid) >= matchScore) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return scoreList.size() - end;
    }

    private void createScoreMap(int idx, String[] conditions, String s) {
        if (idx == 4) {
            int score = Integer.parseInt(conditions[4]);

            if (scoreMap.containsKey(s)) {
                scoreMap.get(s).add(score);
            } else {
                List<Integer> scoreList = new ArrayList<>();
                scoreList.add(score);
                scoreMap.put(s, scoreList);
            }

            return;
        }

        createScoreMap(idx + 1, conditions, s + "-");
        createScoreMap(idx + 1, conditions, s + conditions[idx]);
    }
}