package com.company.baekjun.b2613;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2 {
    static int[] ball;
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        ball = new int[n];

        int start = 0;
        int end = 0;
        input = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            ball[i] = Integer.parseInt(input[i]);
            start = Math.max(start, ball[i]);
            end += ball[i];
        }

        int result = 0;
        while (start <= end) {
            int mid = (start + end) / 2;

            if (checkPossible(mid)) {
                end = mid - 1;
                result = mid;
            } else {
                start = mid + 1;
            }
        }

        int[] groupCntArr = findGroupCnt(result);

        System.out.println(result);
        for (int i = 0; i < m; i++) {
            System.out.printf("%d ", groupCntArr[i]);
        }
    }

    private static int[] findGroupCnt(int maxSum) {
        int[] result = new int[m];

        int sum = 0;
        int idx = 0;
        int groupCnt = 0;
        for (int i = 0; i < n; i++) {
            sum += ball[i];
            if (sum > maxSum) {
                sum = ball[i];
                result[idx] = groupCnt;
                idx++;
                groupCnt = 0;
            }
            groupCnt++;

            if (n - i == m - idx) {
                break;
            }
        }

        while (idx < m) {
            result[idx] = groupCnt;
            idx++;
            groupCnt = 1;
        }

        return result;
    }

    private static boolean checkPossible(int maxSum) {
        int sum = 0;
        int groupCnt = 1;
        for (int i = 0; i < n; i++) {
            sum += ball[i];
            if (sum > maxSum) {
                sum = ball[i];
                groupCnt++;
            }
        }

        return groupCnt <= m;
    }
}