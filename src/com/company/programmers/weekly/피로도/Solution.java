package com.company.programmers.weekly.피로도;

import java.util.*;

class Solution {
    static int maxCount = 0;

    public int solution(int k, int[][] dungeons) {
        dfs(k, dungeons, new ArrayList<>(), new boolean[dungeons.length]);

        return maxCount;
    }

    private void dfs(int k, int[][] dungeons, List<Integer> idxList, boolean[] visited) {
        if (idxList.size() == dungeons.length) {
            int count = 0;
            for (Integer idx : idxList) {
                int minP = dungeons[idx][0];
                int lostP = dungeons[idx][1];

                if (minP <= k) {
                    k -= lostP;
                    count++;
                }
            }

            maxCount = Math.max(maxCount, count);

            return;
        }

        for (int i = 0; i < dungeons.length; i++) {
            if (visited[i]) {
                continue;
            }

            idxList.add(i);
            visited[i] = true;
            dfs(k, dungeons, idxList, visited);
            visited[i] = false;
            idxList.remove(idxList.size() - 1);
        }
    }
}