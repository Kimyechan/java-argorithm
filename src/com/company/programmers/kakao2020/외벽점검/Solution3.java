package com.company.programmers.kakao2020.외벽점검;

import java.util.ArrayList;
import java.util.List;

public class Solution3 {
    int count = 0;
    public int solution(int n, int[] weak, int[] dist) {
        int answer = 0;

        for (int size = 1; size <= dist.length; size++) {
            boolean[] visited = new boolean[dist.length];
            permutation(size, visited, n, weak, dist, new ArrayList<>());

            if (count != 0) {
                break;
            }
        }

        if (count == 0) {
            answer = -1;
        } else {
            answer = count;
        }

        return answer;
    }

    private void permutation(int size, boolean[] visited, int n, int[] weak, int[] dist, List<Integer> orders) {
        if (size == orders.size()) {
            List<Integer> weakList = new ArrayList<>();
            for (int w : weak) {
                weakList.add(w);
            }

            int wLen = weak.length;
            for (int i = 0; i < wLen; i++) {
                int wIdx = 0;
                for (Integer order : orders) {
                    int d = weakList.get(wIdx) + order;
                    while (wIdx < wLen && weakList.get(wIdx) <= d) {
                        wIdx++;
                    }
                }
                if (wIdx >= wLen) {
                    count = size;
                    break;
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
            orders.add(dist[i]);
            permutation(size, visited, n, weak, dist, orders);
            orders.remove(orders.size() - 1);
            visited[i] = false;
        }
    }

    public static void main(String[] args) {
        Solution3 solution = new Solution3();
        System.out.println(solution.solution(12, new int[]{1, 5, 6, 10}, new int[]{1, 2, 3, 4}));
//        System.out.println(solution.solution(12, new int[]{1, 3, 4, 9, 10}, new int[]{3, 5, 7}));
    }
}