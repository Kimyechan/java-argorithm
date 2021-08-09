package com.company.baekjun.b1744;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main2 {
    static int n;
    static int[] numbers;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(numbers);

        int nLen = numbers.length;
        int maxSum = 0;

        int leftIdx = 0;
        while (true) {
            if (leftIdx == nLen - 1) {
                maxSum += numbers[leftIdx];
                leftIdx += 1;
                break;
            }

            if (numbers[leftIdx] < 0) {
                if (numbers[leftIdx + 1] <= 0) {
                    maxSum += numbers[leftIdx] * numbers[leftIdx + 1];
                    leftIdx += 2;
                } else {
                    maxSum += numbers[leftIdx];
                    leftIdx += 1;
                }
            } else {
                break;
            }
        }

        while (leftIdx < nLen && numbers[leftIdx] == 0) {
            leftIdx += 1;
        }

        int rightIdx = nLen - 1;
        while (rightIdx >= leftIdx) {
            if (rightIdx == leftIdx) {
                maxSum += numbers[rightIdx];
                break;
            }

            if (numbers[rightIdx] != 1 && numbers[rightIdx - 1] != 1) {
                maxSum += numbers[rightIdx] * numbers[rightIdx - 1];
            } else {
                maxSum += numbers[rightIdx] + numbers[rightIdx - 1];
            }
            rightIdx -= 2;
        }

        System.out.println(maxSum);
    }
}