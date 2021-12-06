package com.company.programmers.weekly.피로도;

import java.util.ArrayList;
import java.util.List;

public class Solution2 {
    int maxClearCount = 0;

    public int solution(int k, int[][] dungeons) {
        boolean[] visited = new boolean[dungeons.length];
        dfs(k, dungeons, visited, new ArrayList<>());

        return maxClearCount;
    }

    private void dfs(int k, int[][] dungeons, boolean[] visited, List<Integer> orders) {
        if (orders.size() == dungeons.length) {
            int hp = k;
            int clearCount = 0;

            for (Integer order : orders) {
                if (dungeons[order][0] > hp) {
                    break;
                }

                hp -= dungeons[order][1];
                clearCount++;
            }

            maxClearCount = Math.max(maxClearCount, clearCount);

            return;
        }

        for (int i = 0; i < dungeons.length; i++) {
            if (visited[i]) {
                continue;
            }

            orders.add(i);
            visited[i] = true;
            dfs(k, dungeons, visited, orders);
            visited[i] = false;
            orders.remove(orders.size() - 1);
        }
    }

    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
        System.out.println(solution2.solution(80, new int[][]{{80,20},{50,40},{30,10}}));
    }
}
