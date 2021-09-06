package com.company.programmers.kakao2021.매출하락최소화;

import java.util.ArrayList;
import java.util.List;

public class Solution2 {
    static int[][] dp = new int[300001][2];
    static int[] saleInfo;
    static List<List<Integer>> nodeList = new ArrayList<>();

    public int solution(int[] sales, int[][] links) {
        int answer = 0;
        initTree(sales, links);

        answer = Math.min(treeDP(1, 0), treeDP(1, 1));
        return answer;
    }

    private int treeDP(int num, int include) {
        if (dp[num][include] != -1) {
            return dp[num][include];
        }

        dp[num][include] = 0;
        int ret = 0;

        if (include == 1) {
            for (Integer child : nodeList.get(num)) {
                ret += Math.min(treeDP(child, 0), treeDP(child, 1));
            }
            dp[num][include] = (ret + saleInfo[num - 1]);
            return dp[num][include];
        } else {
            boolean flag = false;
            int diff = (nodeList.get(num).size() > 0) ? Integer.MAX_VALUE : 0;
            for (Integer child : nodeList.get(num)) {
                int caseNotInclude = treeDP(child,  0);
                int caseInclude = treeDP(child, 1);

                if (caseInclude < caseNotInclude) {
                    flag = true;
                } else {
                    diff = Math.min(diff, caseInclude - caseNotInclude);
                }
                ret += Math.min(caseInclude, caseNotInclude);
            }
            dp[num][include] = flag ? ret : ret + diff;
        }

        return dp[num][include];
    }

    private void initTree(int[] sales, int[][] links) {
        saleInfo = sales;

        for (int i = 0; i < sales.length + 1; i++) {
            nodeList.add(new ArrayList<>());
        }

        for (int[] link : links) {
            nodeList.get(link[0]).add(link[1]);
        }

        for (int i = 0; i < sales.length + 1; i++) {
            dp[i][0] = -1;
            dp[i][1] = -1;
        }
    }
}