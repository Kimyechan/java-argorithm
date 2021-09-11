package com.company.programmers.kakao2022.s4;

public class Solution {
    static int maxScore;
    static int[] result;

    public int[] solution(int n, int[] info) {
        int[] answer = {};
        result = new int[n];

        combination(0, n, new int[11], info, 0);

        if (maxScore == 0) {
            answer = new int[]{-1};
        } else {
            answer = result;
        }

        return answer;
    }

    private void combination(int idx, int n, int[] lion, int peach[], int count) {
        if (count == n) {
            int[] lionTemp = new int[11];
            System.arraycopy(lion, 0, lionTemp, 0, 11);

            int lionScore = 0;
            int peachScore = 0;
            for (int i = 0; i < 11; i++) {
                if (lionTemp[i] == 0 && peach[i] == 0) {
                    continue;
                }
                if (lionTemp[i] > peach[i]) {
                    lionScore += 10 - i;
                } else {
                    peachScore += 10 - i;
                }
            }

            if (lionScore > peachScore) {
                if (maxScore <= lionScore - peachScore) {
                    maxScore = lionScore - peachScore;
                    result = lionTemp;
                }
            }
            return;
        }

        for (int i = idx; i < 11; i++) {
            lion[i] += 1;
            combination(i, n, lion, peach, count + 1);
            lion[i] -= 1;
        }
    }
}