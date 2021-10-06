package com.company.baekjun.b2014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int k = Integer.parseInt(input[0]);
        int n = Integer.parseInt(input[1]);

        long[] primes = new long[k];
        Queue<Long> pq = new PriorityQueue<>();

        input = br.readLine().split(" ");

        for (int i = 0; i < k; i++) {
            primes[i] = Integer.parseInt(input[i]);
            pq.offer(primes[i]);
        }

        for (int i = 0; i < n - 1; i++) {
            long num = pq.poll();

            for (int j = 0; j < k; j++) {
                pq.offer(num * primes[j]);

                if (num % primes[j] == 0) {
                    break;
                }
            }
        }

        long result = pq.poll();
        System.out.println(result);
    }
}