package com.company.baekjun.b1931;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] times = new int[n][2];

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            times[i][0] = a;
            times[i][1] = b;
        }

        Arrays.sort(times, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] > o2[1]) {
                    return 1;
                } else if(o1[1] == o2[1]) {
                    return o1[0] > o2[0] ? 1 : -1;
                } else {
                    return - 1;
                }
            }
        });

        int result = 0;
        int endTime = 0;
        for (int i = 0; i < n; i++) {
            if (times[i][0] >= endTime) {
                result += 1;
                endTime = times[i][1];
            }
        }

        System.out.println(result);
    }
}
