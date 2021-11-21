package com.company.programmers.problem.예산;

import java.util.Arrays;

class Solution {
    public int solution(int[] d, int budget) {
        int answer = 0;
        Arrays.sort(d);

        for (int i : d) {
            budget -= i;
            if (budget >= 0) {
                answer += 1;
            } else {
                break;
            }
        }

        return answer;
    }
}
