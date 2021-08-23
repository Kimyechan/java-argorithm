package com.company.programmers.kakao2018.다트게임;

import java.util.Arrays;

public class Solution {
    static int[] scores = new int[3];

    public int solution(String dartResult) {
        int answer = 0;

        initScore(dartResult);
        int scoreIdx = -1;
        for (int i = 0; i < dartResult.length(); i++) {
            char c = dartResult.charAt(i);
            if (checkIsNumber(c)) {
                if (i != 0 && checkIsNumber(dartResult.charAt(i - 1))) {
                    continue;
                }
                scoreIdx++;
            } else {
                if (c == 'S' || c == 'D' || c == 'T') {
                    powNumber(scoreIdx, c);
                } else if (c == '*') {
                    applyStarEffect(scoreIdx);
                } else if (c == '#') {
                    applyAchaEffect(scoreIdx);
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            answer += scores[i];
        }

        return answer;
    }

    private void applyAchaEffect(int idx) {
        scores[idx] = -1 * scores[idx];
    }

    private void applyStarEffect(int idx) {
        if (idx == 0) {
            scores[idx] = scores[idx] * 2;
        } else {
            for (int i = idx - 1; i <= idx; i++) {
                scores[i] = scores[i] * 2;
            }
        }
    }

    private void powNumber(int idx, char pow) {
        if (pow == 'D') {
            scores[idx] = (int) Math.pow(scores[idx], 2);
        } else if (pow == 'T') {
            scores[idx] = (int) Math.pow(scores[idx], 3);
        }
    }

    private void initScore(String dartResult) {
        String[] scoreString = dartResult.split("[SDT*#]+");
        for (int i = 0; i < 3; i++) {
            scores[i] = Integer.parseInt(scoreString[i]);
        }
    }

    private boolean checkIsNumber(char c) {
        return c - '0' <= 9 && c - '0' >= 0;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution("1D2S3T*"));
    }
}