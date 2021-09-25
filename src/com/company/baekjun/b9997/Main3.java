package com.company.baekjun.b9997;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main3 {
    static int ALPHABET = (1 << 26) - 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] words = new int[n];

        for (int i = 0; i < n; i++) {
            String word = br.readLine();
            int aSum = 0;
            for (int j = 0; j < word.length(); j++) {
                int a = word.charAt(j) - 'a';
                aSum |= 1 << a;
            }
            words[i] = aSum;
        }

        int totalCount = 0;
        for (int i = 1; i <= n; i++) {
            totalCount += combination(0, 0, i, words, 0);
        }

        System.out.println(totalCount);
    }

    private static int combination(int start, int count, int size, int[] words, int mask) {
        if (count == size) {
            if (mask == ALPHABET) {
                return 1;
            } else {
                return 0;
            }
        }

        int correctCount = 0;
        for (int i = start; i < words.length; i++) {
            correctCount += combination(i + 1, count + 1, size, words, mask | words[i]);
        }

        return correctCount;
    }
}