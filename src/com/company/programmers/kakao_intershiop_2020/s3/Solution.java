package com.company.programmers.kakao_intershiop_2020.s3;

import java.util.*;

class Solution {
    static Set<String> gemSet = new HashSet<>();
    static Map<String, Integer> gemMap = new HashMap<>();

    public int[] solution(String[] gems) {
        int gemKind = 0;
        gemSet.addAll(Arrays.asList(gems));

        gemKind = gemSet.size();
        gemSet.clear();

        int minLen = Integer.MAX_VALUE;
        int minS = 0;
        int minE = 0;

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
                    minLen = Math.min(minLen, end - start);
                    minS = start + 1;
                    minE = end + 1;
                }
                gemMap.put(gems[start], gemMap.get(gems[start]) - 1);
                if (gemMap.get(gems[start]) == 0) {
                    gemSet.remove(gems[start]);
                }
                start += 1;
            }
        }

        return new int[]{minS, minE};
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.solution(new String[]{"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"})));
    }
}