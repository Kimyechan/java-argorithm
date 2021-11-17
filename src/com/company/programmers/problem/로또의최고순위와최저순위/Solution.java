package com.company.programmers.problem.로또의최고순위와최저순위;

import java.util.*;

class Solution {
    Set<Integer> winSet = new HashSet<>();

    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];

        for (int winNum : win_nums) {
            winSet.add(winNum);
        }

        int unknownCount = 0;
        int matchCount = 0;
        for (int lotto : lottos) {
            if (lotto == 0) {
                unknownCount += 1;
                continue;
            }

            if (winSet.contains(lotto)) {
                matchCount += 1;
            }
        }

        answer[0] = unknownCount + matchCount <= 1 ? 6 : 7 - unknownCount - matchCount;
        answer[1] = matchCount <= 1 ? 6 : 7 - matchCount;

        return answer;
    }
}
