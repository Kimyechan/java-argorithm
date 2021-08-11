package com.company.baekjun.b1644;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int target;
    static boolean[] arr;
    static List<Integer> primeNumbers = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        target = Integer.parseInt(br.readLine());

        arr = new boolean[target + 1];
        markPrimeNumber(target);

        extractPrimeNumberList(target);

        int count = countMatchingSum();

        System.out.println(count);
    }

    private static int countMatchingSum() {
        int count = 0;
        int end = 0;
        int sum = 0;
        for (int start = 0; start < primeNumbers.size(); start++) {
            while (sum < target && end < primeNumbers.size()) {
                sum += primeNumbers.get(end);
                end++;
            }

            if (sum == target) {
                count++;
            }

            sum -= primeNumbers.get(start);
        }

        return count;
    }

    private static void markPrimeNumber(int target) {
        arr[0] = true;
        arr[1] = true;
        for (int i = 2; i <= target; i++) {
            if (arr[i]) {
                continue;
            }

            for (int j = i * 2; j <= target; j += i) {
                arr[j] = true;
            }
        }
    }

    private static void extractPrimeNumberList(int target) {
        for (int i = 2; i <= target; i++) {
            if (arr[i]) {
                continue;
            }
            primeNumbers.add(i);
        }
    }
}