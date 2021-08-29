package com.company.programmers.kakao2019.실패율;

import java.util.ArrayList;
import java.util.List;

public class Solution3 {
    static List<Stage> stageList = new ArrayList<>();

    static class Stage {
        int num;
        long failCount;
        long totalArriveCount;

        public Stage(int num, long failCount, long totalArriveCount) {
            this.num = num;
            this.failCount = failCount;
            this.totalArriveCount = totalArriveCount;
        }
    }

    public int[] solution(int N, int[] stages) {
        int[] answer = {};

        int[] arriveCounts = new int[N + 2];
        for (int stage : stages) {
            arriveCounts[stage] += 1;
        }

        for (int i = 1; i < N + 1; i++) {
            int failCount = arriveCounts[i];
            int totalArriveCount = 0;
            for (int j = i; j < N + 2; j++) {
                totalArriveCount += arriveCounts[j];
            }
            stageList.add(new Stage(i, failCount, totalArriveCount));
        }

        stageList.sort((s1, s2) -> {
            long failRate1 = s2.totalArriveCount == 0 ? s1.failCount : s1.failCount * s2.totalArriveCount;
            long failRate2 = s1.totalArriveCount == 0 ? s2.failCount : s2.failCount * s1.totalArriveCount;

            if (failRate1 == failRate2) {
                return s1.num - s2.num;
            } else {
                return Long.compare(failRate2, failRate1);
            }
        });

        answer = new int[stageList.size()];
        for (int i = 0; i < stageList.size(); i++) {
            answer[i] = stageList.get(i).num;
        }

        return answer;
    }
}