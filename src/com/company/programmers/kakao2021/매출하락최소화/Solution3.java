package com.company.programmers.kakao2021.매출하락최소화;

import java.util.ArrayList;
import java.util.List;

public class Solution3 {
    List<List<Integer>> tree = new ArrayList<>();
    int[][] dp = new int[300001][2];
    int[] saleInfo;

    public int solution(int[] sales, int[][] links) {
        int answer;

        init(sales, links);

        answer = Math.min(findMinSale(1, 0),  findMinSale(1, 1));

        return answer;
    }

    private int findMinSale(int num, int include) {
        if (dp[num][include] != 0) {
            return dp[num][include];
        }

        int ret = 0;
        if (include == 1) {
            for (Integer employee : tree.get(num)) {
                int caseSend = findMinSale(employee, 1);
                int caseNotSend = findMinSale(employee, 0);
                ret += Math.min(caseSend, caseNotSend);
            }
            dp[num][include] = ret + saleInfo[num - 1];
        } else {
            boolean flag = false;
            int diff = tree.get(num).size() > 0 ? Integer.MAX_VALUE : 0;
            for (Integer employee : tree.get(num)) {
                int caseSend = findMinSale(employee, 1);
                int caseNotSend = findMinSale(employee, 0);

                if (caseSend < caseNotSend) {
                    flag = true;
                } else {
                    diff = Math.min(diff, caseSend - caseNotSend);
                }
                ret += Math.min(caseSend, caseNotSend);
            }
            dp[num][include] = flag ? ret : ret + diff;
        }

        return dp[num][include];
    }

    private void init(int[] sales, int[][] links) {
        saleInfo = sales;

        for (int i = 0; i < sales.length + 1; i++) {
            tree.add(new ArrayList<>());
        }

        for (int[] link : links) {
            tree.get(link[0]).add(link[1]);
        }
    }

    public static void main(String[] args) {
        Solution3 solution3 = new Solution3();
        System.out.println(solution3.solution(
                new int[]{14, 17, 15, 18, 19, 14, 13, 16, 28, 17},
                new int[][]{
                        {10, 8}, {1, 9}, {9, 7}, {5, 4}, {1, 5}, {5, 10}, {10, 6}, {1, 3}, {10, 2}
                }
        ));
    }
}