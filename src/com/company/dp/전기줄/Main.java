package com.company.dp.전기줄;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static class Line {
        int a;
        int b;

        public Line(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        List<Line> lines = new ArrayList<>();
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            lines.add(new Line(a, b));
        }

        lines.sort((o1, o2) ->
          o1.a > o2.a ? 1 : -1
        );

        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (lines.get(i).b > lines.get(j).b) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int answer = 0;
        for (int i : dp) {
            answer = Math.max(answer, i);
        }

        System.out.println(n - answer);
    }
}
