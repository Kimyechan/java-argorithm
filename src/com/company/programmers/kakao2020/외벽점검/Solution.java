package com.company.programmers.kakao2020.외벽점검;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    static int minPersonCount = Integer.MAX_VALUE;

    public int solution(int n, int[] weak, int[] dist) {
        int answer = 0;

        for (int i = 1; i <= dist.length; i++) {
            List<Integer> friends = new ArrayList<>();
            boolean[] visited = new boolean[dist.length];

            findMinUsePerson(i, dist, visited, friends, n, weak);
            if (minPersonCount != Integer.MAX_VALUE) {
                break;
            }
        }

        if (minPersonCount == Integer.MAX_VALUE) {
            answer = -1;
        } else {
            answer = minPersonCount;
        }

        return answer;
    }

    private void findMinUsePerson(int size, int[] dist, boolean[] visited, List<Integer> friends, int n, int[] weak) {
        if (friends.size() == size) {
            List<Integer> weakList = new ArrayList<>();
            for (int w : weak) {
                weakList.add(w);
            }

            for (int i = 0; i < weakList.size(); i++) {
                int idx = 0;
                for (Integer friend : friends) {
                    int moveDis = weakList.get(idx) + friend;
                    idx += 1;
                    while (idx < weakList.size() && weakList.get(idx) <= moveDis) {
                        idx += 1;
                    }
                    if (idx >= weakList.size()) {
                        break;
                    }
                }
                if (idx >= weakList.size()) {
                    minPersonCount = Math.min(minPersonCount, friends.size());
                    return;
                }
                int firstWeak = weakList.get(0);
                weakList.remove(0);
                weakList.add(firstWeak + n);
            }

            return;
        }

        for (int i = 0; i < dist.length; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            friends.add(dist[i]);
            findMinUsePerson(size, dist, visited, friends, n, weak);
            friends.remove(friends.size() - 1);
            visited[i] = false;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(12, new int[]{1, 5, 6, 10}, new int[]{1, 2, 3, 4}));
        System.out.println(solution.solution(12, new int[]{1, 3, 4, 9, 10}, new int[]{3, 5, 7}));
    }
}