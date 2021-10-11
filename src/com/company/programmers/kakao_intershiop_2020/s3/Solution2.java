package com.company.programmers.kakao_intershiop_2020.s3;

import java.util.*;

public class Solution2 {
    Set<String> gemSet = new HashSet<>();
    Map<String, Integer> gemMap = new HashMap<>();

    public int[] solution(String[] gems) {
        gemSet.addAll(Arrays.asList(gems));
        int gemKind = gemSet.size();
        gemSet.clear();

        int minLen = Integer.MAX_VALUE;
        int minStart = 0;
        int minEnd = 0;
        int start = 0;
        int end = -1;
        while (start < gems.length) {
            if (gemSet.size() < gemKind) {
                end += 1;
                if (end >= gems.length) {
                    break;
                }
                gemSet.add(gems[end]);
                gemMap.put(gems[end], gemMap.getOrDefault(gems[end], 0) + 1);
            } else {
                if (minLen > end - start) {
                    minLen = end - start;
                    minStart = start + 1;
                    minEnd = end + 1;
                }
                gemMap.put(gems[start], gemMap.get(gems[start]) - 1);
                if (gemMap.get(gems[start]) == 0) {
                    gemSet.remove(gems[start]);
                }
                start += 1;
            }
        }

        return new int[]{minStart, minEnd};
    }

    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        System.out.println(Arrays.toString(solution.solution(new String[]{"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"})));
    }
}
