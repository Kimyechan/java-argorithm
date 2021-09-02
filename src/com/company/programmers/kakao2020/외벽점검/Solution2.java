package com.company.programmers.kakao2020.외벽점검;

import java.util.ArrayList;
import java.util.List;

public class Solution2 {
    static int minCount = Integer.MAX_VALUE;

    public int solution(int n, int[] weak, int[] dist) {
        int answer = 0;

        for (int i = 1; i <= dist.length; i++) {
            List<Integer> distList = new ArrayList<>();
            boolean[] visited = new boolean[dist.length];

            findMinCount(i, visited, distList, dist, weak, n);
            if (minCount != Integer.MAX_VALUE) {
                break;
            }
        }

        if (minCount == Integer.MAX_VALUE) {
            answer = -1;
        } else {
            answer = minCount;
        }

        return answer;
    }

    private void findMinCount(int size, boolean[] visited, List<Integer> distList, int[] dist, int[] weak, int n) {
        if (distList.size() == size) {
            List<Integer> weakList = new ArrayList<>();
            for (int w : weak) {
                weakList.add(w);
            }

            loop:
            for (int i = 0; i < weakList.size(); i++) {
                int idx = 0;
                for (Integer d : distList) {
                    int moveDis = weakList.get(idx) + d;
                    while (idx < weakList.size() && weakList.get(idx) <= moveDis) {
                        idx++;
                    }
                    if (idx >= weakList.size()) {
                        minCount = size;
                        break loop;
                    }
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
            distList.add(dist[i]);
            findMinCount(size, visited, distList, dist, weak, n);
            distList.remove(distList.size() - 1);
            visited[i] = false;
        }
    }
}