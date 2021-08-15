package com.company.baekjun.b11000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main3 {
    static int n;
    static int[][] times;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        times = new int[n][2];

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            times[i][0] = Integer.parseInt(input[0]);
            times[i][1] = Integer.parseInt(input[1]);
        }

        Arrays.sort(times, (t1, t2) -> {
            if (t1[0] == t2[0]) {
                return t1[1] - t2[1];
            } else {
                return t1[0] - t2[0];
            }
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(times[0][1]);
        for (int i = 1; i < n; i++) {
            if (pq.peek() <= times[i][0]) {
                    pq.poll();
            }

            pq.offer(times[i][1]);
        }

        System.out.println(pq.size());
    }
}