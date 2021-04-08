package com.company.programmers.고득점Kit.dp.도둑질;

class Solution {
    public int solution(int[] money) {
        int n = money.length;

        // dp = 순차대로 진행했을 때 현재 최대 값
        int[] dp1 = new int[n]; // 첫번째 방 선택
        int[] dp2 = new int[n]; // 첫번째 방 선택 X, 두번쩨 선택
        int[] dp3 = new int[n]; // 첫번째 두번째 X, 세번째 선택

        dp1[0] = money[0];
        dp1[1] = money[0];
        dp2[1] = money[1];
        for (int i = 2; i < n - 1; i++) {
            dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + money[i]);
            dp2[i] = Math.max(dp2[i - 1], dp2[i - 2] + money[i]);
            dp3[i] = Math.max(dp3[i - 1], dp3[i - 2] + money[i]);
        }

        dp1[n - 1] = dp1[n - 2];
        dp2[n - 1] = Math.max(dp2[n - 2], dp2[n - 3] + money[n - 1]);
        dp3[n - 1] = Math.max(dp3[n - 2], dp3[n - 3] + money[n - 1]);

        int max1 = Math.max(dp1[n - 1], dp2[n - 1]);
        int max2 = Math.max(max1, dp3[n - 1]);

        return max2;
    }
}


//class Solution {
//    public int solution(int[] money) {
//        int n = money.length;
//
//        // dp = 순차대로 진행했을 때 현재 최대 값
//        int[] dp1 = new int[n]; // 첫번째 방 선택
//        int[] dp2 = new int[n]; // 첫번째 방 선택 X, 두번쩨 선택
//        int[] dp3 = new int[n]; // 첫번째 두번째 X, 세번째 선택
//
//        // 1
//        dp1[0] = money[0];
//        dp1[1] = money[0];
//        for (int i = 2; i < n - 1; i++) {
//            dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + money[i]);
//        }
//
//        // 2
//        dp2[1] = money[1];
//        for (int i = 2; i < n; i++) {
//            dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + money[i]);
//        }
//
//        // 3
//        dp3[2] = money[2];
//        for (int i = 3; i < n; i++) {
//            dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + money[i]);
//        }
//        int max = Math.max(Math.max(dp1[n - 2], dp2[n - 1]), dp3[n - 1]);
//
//        return max;
//    }
//}
