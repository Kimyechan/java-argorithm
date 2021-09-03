package com.company.programmers.kakao2021.순위검색;

import java.util.*;

public class Solution2 {
    static Map<String, List<Integer>> conditionMap = new HashMap<>();

    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        for (int i = 0; i < info.length; i++) {
            insertInfoToMap(info[i]);
        }

        for (String key : conditionMap.keySet()) {
            List<Integer> scoreList = conditionMap.get(key);
            Collections.sort(scoreList);

            conditionMap.put(key, scoreList);
        }

        for (int i = 0; i < query.length; i++) {
            query[i] = query[i].replaceAll(" and ", " ");
            String[] conditionSearch = query[i].split(" ");

            StringBuilder key = new StringBuilder();
            for (int j = 0; j < 4; j++) {
                key.append(conditionSearch[j]);
            }
            int matchScore = Integer.parseInt(conditionSearch[4]);

            int upperCount = 0;
            if (conditionMap.containsKey(key.toString())) {
                List<Integer> scoreList = conditionMap.get(key.toString());
                upperCount = binarySearch(matchScore, scoreList);
            }

            answer[i] = upperCount;
        }

        return answer;
    }

    private int binarySearch(int matchScore, List<Integer> scoreList) {
        int start = 0;
        int end = scoreList.size();

        while (start < end) {
            int mid = (start + end) / 2;

            if (matchScore <= scoreList.get(mid)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return scoreList.size() - end;
    }

    private void insertInfoToMap(String information) {
        String[] infos = information.split(" ");

        dfs(0, infos, "");
    }

    private void dfs(int idx, String[] infos, String condition) {
        if (idx == 4) {
            int score = Integer.parseInt(infos[4]);
            if (conditionMap.containsKey(condition)) {
                conditionMap.get(condition).add(score);
            } else {
                List<Integer> scores = new ArrayList<>();
                scores.add(score);
                conditionMap.put(condition, scores);
            }
            return;
        }

        dfs(idx + 1, infos, condition + "-");
        dfs(idx + 1, infos, condition + infos[idx]);
    }

    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
        System.out.println(Arrays.toString(solution2.solution(
                new String[]{"java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"},
                new String[]{"java and backend and junior and pizza 100", "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150"})));
    }
}