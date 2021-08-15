package com.company.baekjun.b1644;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main3 {
    static int n;
    static boolean[] isNotPrime;
    static List<Integer> primes = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        isNotPrime = new boolean[n + 1];
        isNotPrime[0] = true;
        isNotPrime[1] = true;

        for (int i = 2; i < n + 1; i++) {
            if (isNotPrime[i]) {
                continue;
            }

            for (int j = i * 2; j < n + 1; j += i) {
                isNotPrime[j] = true;
            }
        }

        for (int i = 0; i < n + 1; i++) {
            if (isNotPrime[i]) {
                continue;
            }
            primes.add(i);
        }

        int end = 0;
        int sum = 0;
        int count = 0;
        for (int start = 0; start < primes.size(); start++) {
            while (end < primes.size() && sum < n) {
                sum += primes.get(end);
                end++;
            }

            if (sum == n) {
                count += 1;
            }

            sum -= primes.get(start);
        }

        System.out.println(count);
    }
}