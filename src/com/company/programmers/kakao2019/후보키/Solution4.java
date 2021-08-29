package com.company.programmers.kakao2019.후보키;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution4 {
    List<Integer> candidateKeys = new ArrayList<>();

    public int solution(String[][] relation) {
        int answer = 0;

        int rowLen = relation.length;
        int colLen = relation[0].length;

        for (int i = 0; i < (1 << colLen); i++) {
            if (checkUniqueness(relation, rowLen, colLen, i) && checkMinimality(i)) {
                candidateKeys.add(i);
            }
        }

        answer = candidateKeys.size();

        return answer;
    }

    private boolean checkMinimality(int key) {
        for (Integer candidateKey : candidateKeys) {
            if ((candidateKey & key) == candidateKey) {
                return false;
            }
        }

        return true;
    }

    private boolean checkUniqueness(String[][] relation, int rowLen, int colLen, int i) {
        Set<String> set = new HashSet<>();
        for (int j = 0; j < rowLen; j++) {
            StringBuilder sb = new StringBuilder();
            for (int k = 0; k < colLen; k++) {
                if ((i & (1 << k)) != 0) {
                    sb.append(relation[j][k]);
                }
            }
            set.add(sb.toString());
        }

        return set.size() == rowLen;
    }
}