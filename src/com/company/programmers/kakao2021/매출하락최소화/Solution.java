package com.company.programmers.kakao2021.매출하락최소화;

import java.util.*;

public class Solution {
    int dp[][] = new int[300001][2];
    ArrayList<ArrayList<Integer>> info = new ArrayList();
    int salesInfo[];

    public void init(int[][] links, int[] sales) {
        salesInfo = sales;
        for (int i = 0; i <= sales.length; i++)
            info.add(new ArrayList<Integer>());

        for (int i = 0; i < links.length; i++)
            info.get(links[i][0]).add(links[i][1]);

        for (int i = 0; i <= sales.length; i++)
            dp[i][0] = dp[i][1] = -1;
    }

    public int solve(int now, int include) {
        if (dp[now][include] != -1)
            return dp[now][include];

        dp[now][include] = 0;
        int ret = 0;

        if (include == 1) {
            for (int i = 0; i < info.get(now).size(); i++) {
                int next = info.get(now).get(i);
                ret += Math.min(solve(next, 1), solve(next, 0));
            }
            return dp[now][include] = (ret + salesInfo[now - 1]);
        } else {
            boolean flag = false;
            int diff = (info.get(now).size() > 0) ? 0x7FFFFFFF : 0;
            for (int i = 0; i < info.get(now).size(); i++) {
                int next = info.get(now).get(i);
                int case1 = solve(next, 1);
                int case2 = solve(next, 0);

                if (case1 < case2)
                    flag = true;
                else
                    diff = Math.min(case1 - case2, diff);

                ret += Math.min(case1, case2);
            }

            return dp[now][include] = flag ? ret : ret + diff;
        }
    }

    public int solution(int[] sales, int[][] links) {
        int answer = 0;
        init(links, sales);
        return answer = Math.min(solve(1, 1), solve(1, 0));
    }
}