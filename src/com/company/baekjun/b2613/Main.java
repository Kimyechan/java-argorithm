package com.company.baekjun.b2613;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n, m;
    static int[] ball;

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

        int result = parametricSearch(start, end);
        int[] resultArray = findGroupSize(result);

        System.out.println(result);
        for (int i = 0; i < m; i++) {
            System.out.printf("%d ", resultArray[i]);
        }
    }

    private static int[] findGroupSize(int maxSize) {
        int[] sizeArray = new int[m];

        int idx = 0;
        int sum = 0;
        int groupSize = 0;
        for (int i = 0; i < n; i++) {
            sum += ball[i];
            if (sum > maxSize) {
                sizeArray[idx] = groupSize;
                sum = ball[i];
                idx++;
                groupSize = 0;
            }
            groupSize++;

            if (n - i == m - idx) {
                break;
            }
        }

        while (idx < m) {
            sizeArray[idx] = groupSize;
            groupSize = 1;
            idx++;
        }

        return sizeArray;
    }

    private static int parametricSearch(int start, int end) {
        while (start < end) {
            int mid = (start + end) / 2;

            if (checkPossible(mid)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return start;
    }

    private static boolean checkPossible(int mid) {
        int groupCnt = 1;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += ball[i];
            if (sum > mid) {
                sum = ball[i];
                groupCnt++;
            }
        }

        return groupCnt <= m;
    }
}