package com.company.programmers.kakao2019.실패율;

import java.util.ArrayList;
import java.util.List;

public class Solution2 {
    static List<Stage> stageList = new ArrayList<>();

    static class Stage {
        int stageNumber;
        long failCount;
        long successCount;

        public Stage(int stageNumber, long failCount, long successCount) {
            this.stageNumber = stageNumber;
            this.failCount = failCount;
            this.successCount = successCount;
        }
    }

    public int[] solution(int N, int[] stages) {
        int[] answer = {};

        int[] stageStay = new int[N + 2];
        for (int stage : stages) {
            stageStay[stage] += 1;
        }

        for (int i = 1; i < N + 1; i++) {
            int successCount = 0;
            for (int j = i; j < N + 2; j++) {
                successCount += stageStay[j];
            }
            stageList.add(new Stage(i, stageStay[i], successCount));
        }

        stageList.sort((s1, s2) -> {
            long f1 = s2.successCount == 0 ? s1.failCount : s1.failCount * s2.successCount;
            long f2 = s1.successCount == 0 ? s2.failCount : s2.failCount * s1.successCount;

            if (f1 == f2) {
                return s1.stageNumber - s2.stageNumber;
            } else if (f1 > f2){
                return -1;
            } else {
                return 1;
            }
        });

        answer = new int[N];
        for (int i = 0; i < N; i++) {
            answer[i] = stageList.get(i).stageNumber;
        }

        return answer;
    }
}