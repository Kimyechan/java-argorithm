package com.company.programmers.kakao2020.외벽점검;

import java.util.*;

public class Solution4 {
    static int minCount = Integer.MAX_VALUE;

    public int solution(int n, int[] weak, int[] dist) {
        int answer = 0;

        boolean[] visited = new boolean[dist.length];
        dfs(n, weak, dist, visited, new ArrayList());

        if (minCount == Integer.MAX_VALUE) {
            answer = -1;
        } else {
            answer = minCount;
        }

        return answer;
    }

    public void dfs(int n, int[] weak, int[] dist, boolean[] visited, List<Integer> orderList) {
        if (orderList.size() == dist.length) {
            List<Integer> weakList = new ArrayList<>();
            for (int i = 0; i < weak.length; i++) {
                weakList.add(weak[i]);
            }

            for (int i = 0; i < weakList.size(); i++) {
                int wIdx = 0;
                int dIdx = 0;
                while (dIdx < orderList.size()) {
                    int d = weakList.get(wIdx) + orderList.get(dIdx);
                    while (wIdx < weakList.size() && weakList.get(wIdx) <= d) {
                        wIdx++;
                    }
                    if (wIdx == weakList.size()) {
                        minCount = Math.min(minCount, dIdx + 1);
                        break;
                    }
                    dIdx++;
                }
                int temp = weakList.get(0);
                weakList.remove(0);
                weakList.add(temp + n);
            }

            return;
        }

        for (int i = 0; i < dist.length; i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            orderList.add(dist[i]);
            dfs(n, weak, dist, visited, orderList);
            orderList.remove(orderList.size() - 1);
            visited[i] = false;
        }
    }
}