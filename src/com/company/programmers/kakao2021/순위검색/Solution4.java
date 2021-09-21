package com.company.programmers.kakao2021.순위검색;

import java.util.*;

class Solution4 {
    static Map<String, List<Integer>> scoreMap = new HashMap<>();

    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];

        for (String inf : info) {
            String[] infos = inf.split(" ");
            addInfo(infos, "", 0);
        }

        for (List<Integer> scoreList : scoreMap.values()){
            Collections.sort(scoreList);
        }

        for (int i = 0; i < query.length; i++) {
            query[i] = query[i].replaceAll(" and ", " ");
            String[] q = query[i].split(" ");
            String condition = q[0] + q[1] + q[2] + q[3];
            Integer score = Integer.parseInt(q[4]);

            List<Integer> scoreList = scoreMap.getOrDefault(condition, new ArrayList<>());
            int passCount = binarySearch(scoreList, score);

            answer[i] = passCount;
        }

        return answer;
    }

    public int binarySearch(List<Integer> scoreList, int score) {
        int start = 0;
        int end = scoreList.size();

        while (start < end) {
            int mid = (start + end) / 2;

            if (score <= scoreList.get(mid)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return scoreList.size() - end;
    }

    public void addInfo(String[] infos, String condition, int idx) {
        if (idx == 4) {
            int score = Integer.parseInt(infos[4]);

            if (scoreMap.containsKey(condition)) {
                scoreMap.get(condition).add(score);
            } else {
                List<Integer> scoreList = new ArrayList<>();
                scoreList.add(score);
                scoreMap.put(condition, scoreList);
            }

            return;
        }

        addInfo(infos, condition + infos[idx], idx + 1);
        addInfo(infos, condition + "-", idx + 1);
    }
}