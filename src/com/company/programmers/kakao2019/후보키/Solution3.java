package com.company.programmers.kakao2019.후보키;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution3 {
    static List<Integer> candidateKeyList = new ArrayList<>();

    public int solution(String[][] relation) {
        int answer = 0;
        int rowLen = relation.length;
        int colLen = relation[0].length;

        for (int i = 0; i < (1 << colLen); i++) {
            if (checkUniqueness(relation, rowLen, colLen, i) && checkMinimality(i)) {
                candidateKeyList.add(i);
            }
        }

        answer = candidateKeyList.size();

        return answer;
    }

    private boolean checkMinimality(int key) {
        for (Integer candidateKey : candidateKeyList) {
            if ((candidateKey & key) == candidateKey) {
                return false;
            }
        }

        return true;
    }

    private boolean checkUniqueness(String[][] relation, int rowLen, int colLen, int key) {
        Set<String> rowSet = new HashSet<>();
        for (int j = 0; j < rowLen; j++) {
            StringBuilder rowCombi = new StringBuilder();
            for (int k = 0; k < colLen; k++) {
                if ((key & (1 << k)) != 0) {
                    rowCombi.append(relation[j][k]);
                }
            }
            rowSet.add(rowCombi.toString());
        }

        return rowSet.size() == rowLen;
    }
}