package com.company.programmers.kakao2021.매출하락최소화;

import java.util.*;

class Solution4 {
    private Map<Integer, List<Integer>> linkMap = new HashMap<>();
    private int[][] dp;
    private int[] saleInfo;

    public int solution(int[] sales, int[][] links) {
        int answer = 0;

        for (int[] link : links) {
            if (linkMap.containsKey(link[0])) {
                linkMap.get(link[0]).add(link[1]);
            } else {
                List<Integer> linkList = new ArrayList<>();
                linkList.add(link[1]);
                linkMap.put(link[0], linkList);
            }
        }
        saleInfo = new int[sales.length];
        for (int i = 0; i < sales.length; i++) {
            saleInfo[i] = sales[i];
        }

        dp = new int[sales.length + 1][2];
        answer = Math.min(treeDP(1, 0), treeDP(1, 1));

        return answer;
    }

    public int treeDP(int employer, int include) {
        if (dp[employer][include] != 0) {
            return dp[employer][include];
        }

        if (include == 1) {
            int money = 0;
            for (Integer employee : linkMap.getOrDefault(employer, new ArrayList<>())) {
                money += Math.min(treeDP(employee, 1), treeDP(employee, 0));
            }
            dp[employer][1] = saleInfo[employer - 1] + money;
        } else {
            boolean flag = linkMap.containsKey(employer) ? true : false;
            int diff = Integer.MAX_VALUE;
            for (Integer employee : linkMap.getOrDefault(employer, new ArrayList<>())) {
                int contain = treeDP(employee, 1);
                int notContain = treeDP(employee, 0);

                if (contain <= notContain) {
                    flag = false;
                    dp[employer][0] += contain;
                } else {
                    diff = Math.min(diff, contain - notContain);
                    dp[employer][0] += notContain;
                }
            }

            dp[employer][0] += flag ? diff : 0;
        }

        return dp[employer][include];
    }
}