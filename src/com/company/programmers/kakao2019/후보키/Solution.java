package com.company.programmers.kakao2019.후보키;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    List<Integer> uniqueKeyList = new ArrayList<>();

    public int solution(String[][] relation) {
        int rowLen = relation.length;
        int colLen = relation[0].length;

        for (int i = 0; i < (1 << colLen); i++) {
            Set<String> set = new HashSet<>();
            for (int j = 0; j < rowLen; j++) {
                StringBuilder key = new StringBuilder();
                for (int k = 0; k < colLen; k++) {
                    if ((i & (1 << k)) != 0) {
                        key.append(relation[j][k]);
                    }
                }
                set.add(key.toString());
            }

            if (set.size() != rowLen) {
                continue;
            }

            if (checkUniqueKey(i)) {
                uniqueKeyList.add(i);
            }
        }

        return uniqueKeyList.size();
    }

    private boolean checkUniqueKey(int key) {
        for (Integer uniqueKey : uniqueKeyList) {
            if ((key & uniqueKey) == uniqueKey) {
                return false;
            }
        }

        return true;
    }
}