package com.company.programmers.kakao2019.실패율;

import java.util.*;

public class Solution {
    static int[] stageArriveCount;
    static Map<Integer, FailRate> stageMap = new HashMap<>();

    static class FailRate {
        int stageNumber;
        long notClearCount;
        long arriveCount;

        public FailRate(int stageNumber, long notClearCount, long arriveCount) {
            this.stageNumber = stageNumber;
            this.notClearCount = notClearCount;
            this.arriveCount = arriveCount;
        }
    }

    public int[] solution(int N, int[] stages) {
        int[] answer = {};
        stageArriveCount = new int[N + 2];
        for (int stage : stages) {
            stageArriveCount[stage] += 1;
        }

        for (int i = 1; i <= N; i++) {
            int notClearCount = stageArriveCount[i];
            int arriveCount = 0;
            for (int j = i; j <= N + 1; j++) {
                arriveCount += stageArriveCount[j];
            }
            stageMap.put(i, new FailRate(i, notClearCount, arriveCount));
        }

        List<FailRate> stageNumber = new ArrayList<>(stageMap.values());
        stageNumber.sort((f1, f2) -> {
            Long v1 = f2.arriveCount == 0 ? f1.notClearCount : f1.notClearCount * f2.arriveCount;
            Long v2 = f1.arriveCount == 0 ? f2.notClearCount : f2.notClearCount * f1.arriveCount;

            if (v1.equals(v2)) {
                return f1.stageNumber - f2.stageNumber;
            } else {
                return v1.compareTo(v2) * -1;
            }
        });

        answer = new int[stageNumber.size()];
        for (int i = 0; i < stageNumber.size(); i++) {
            answer[i] = stageNumber.get(i).stageNumber;
        }

        return answer;
    }
}