package com.company.baekjun.b9997;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main4 {
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

        int totalCount = combination(words,0,0);

        System.out.println(totalCount);
    }

    private static int combination(int[] words, int idx, int mask) {
        if (idx >= words.length) {
            if (mask == ALPHABET) {
                return 1;
            } else {
                return 0;
            }
        }

        int correctCount = 0;
        correctCount += combination(words, idx + 1, mask | words[idx]);
        correctCount += combination(words, idx + 1, mask);

        return correctCount;
    }
}