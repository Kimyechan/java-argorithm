package com.company.baekjun.b9997;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2 {
    static int[] words;
    static int ALPHABET = (1 << 26) - 1;
    static int possibleCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        words = new int[n];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            int w = 0;
            for (int j = 0; j < str.length(); j++) {
                int a = str.charAt(j) - 'a';
                w |= (1 << a);
            }
            words[i] = w;
        }

        combination(0, 0);

        System.out.println(possibleCount);
    }

    private static void combination(int idx, int mask) {
        if (idx == words.length) {
            if ((mask & ALPHABET) == ALPHABET) {
                possibleCount++;
            }
            return;
        }

        combination(idx + 1, mask | words[idx]);
        combination(idx + 1,  mask);
    }
}