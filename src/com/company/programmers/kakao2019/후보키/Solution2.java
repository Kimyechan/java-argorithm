package com.company.programmers.kakao2019.후보키;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution2 {
    static List<HashSet<Integer>> candidateKeys = new ArrayList<>();

    public int solution(String[][] relation) {
        int answer = 0;
        int colLen = relation[0].length;

        for (int i = 1; i < colLen + 1; i++) {
            combination(0, i, new HashSet<>(), relation);
        }

        answer = candidateKeys.size();

        return answer;
    }

    private void combination(int start, int size, HashSet<Integer> keySet, String[][] relation) {
        if (keySet.size() == size) {
            if (keySet.contains(1) && keySet.contains(2)) {
                System.out.println("");
            }

            if (checkUniqueKey(keySet, relation) && checkMinimumKey(keySet)) {
                candidateKeys.add(keySet);
            }
            return;
        }

        for (int i = start; i < relation[0].length; i++) {
            HashSet<Integer> newKeySet = new HashSet<>(keySet);
            newKeySet.add(i);
            combination(i + 1, size, newKeySet, relation);
//            keySet.add(i);  // candidateList에 있는 set 객체를 변경시킨다
//            combination(i + 1, size, keySet, relation);
//            keySet.remove(i);
        }
    }

    private boolean checkMinimumKey(HashSet<Integer> keySet) {
        for (HashSet<Integer> candidateKey : candidateKeys) {
            if (keySet.containsAll(candidateKey)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkUniqueKey(HashSet<Integer> keySet, String[][] relation) {
        Set<String> rowSet = new HashSet<>();
        for (int i = 0; i < relation.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (Integer col : keySet) {
                sb.append(relation[i][col]);
            }
            rowSet.add(sb.toString());
        }

        return rowSet.size() == relation.length;
    }

    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
        System.out.println(solution2.solution(new String[][]{
                {"100", "ryan", "music", "2"},
                {"200", "apeach", "math", "2"},
                {"300", "tube", "computer", "3"},
                {"400", "con", "computer", "4"},
                {"500", "muzi", "music", "3"},
                {"600", "apeach", "music", "2"}
        }));
    }
}