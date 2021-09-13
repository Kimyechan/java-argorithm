package com.company.programmers.weekly.week2;

class Solution {
    public String solution(int[][] scores) {
        StringBuilder answer = new StringBuilder();

        int len = scores.length;
        for (int i = 0; i < len; i++) {
            int self = scores[i][i];
            int totalSum = 0;
            boolean maxSelf = true;
            boolean minSelf = true;
            for (int j = 0; j < len; j++) {
                if (i != j && scores[j][i] >= self) {
                    maxSelf = false;
                }
                if (i != j && scores[j][i] <= self) {
                    minSelf = false;
                }
                totalSum += scores[j][i];
            }

            double avg = 0;
            if (maxSelf || minSelf) {
                avg = (double) (totalSum - self) / (len - 1);
            } else {
                avg = (double) totalSum / len;
            }

            String level = calcLevel(avg);
            answer.append(level);
        }

        return answer.toString();
    }

    public String calcLevel(double avg) {
        if (avg >= 90.0) {
            return "A";
        } else if (avg >= 80) {
            return "B";
        } else if (avg >= 70) {
            return "C";
        } else if (avg >= 50) {
            return "D";
        } else {
            return "F";
        }
    }
}
