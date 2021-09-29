package com.company.baekjun.b2512;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] taxes = new int[n];
        int totalTax = 0;

        String[] input = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            taxes[i] = Integer.parseInt(input[i]);
        }
        totalTax = Integer.parseInt(br.readLine());

        int totalNeedTax = 0;
        for (int i = 0; i < n; i++) {
            totalNeedTax += taxes[i];
        }

        int maxTax = 0;
        for (int i = 0; i < n; i++) {
            maxTax = Math.max(maxTax, taxes[i]);
        }
        if (totalNeedTax <= totalTax) {
            System.out.println(maxTax);
        } else {
            int start = 0;
            int end = maxTax;
            while (start < end) {
                int mid = (start + end) / 2;

                int taxSum = 0;
                for (int i = 0; i < n; i++) {
                    taxSum += Math.min(mid, taxes[i]);
                }

                if (taxSum > totalTax) {
                    end = mid;
                } else {
                    start = mid + 1;
                }
            }
            System.out.println(start - 1);
        }
    }
}
//5
//        70 80 110 100 100
//        450