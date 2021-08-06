package com.company.baekjun.b1339;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main3 {
    static int n;
    static String[] words;
    static int[] alphabets = new int[26];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        words = new String[n];
        for (int i = 0; i < n; i++) {
            words[i] = br.readLine();
        }

        for (int i = 0; i < n; i++) {
            int digit = (int) Math.pow(10, words[i].length() - 1);
            for (int j = 0; j < words[i].length(); j++) {
                alphabets[words[i].charAt(j) - 'A'] += digit;
                digit /= 10;
            }
        }

        Arrays.sort(alphabets);

        int num = 9;
        int maxSum = 0;
        for (int i = 0; i < 10; i++) {
            maxSum += alphabets[25 - i] * num;
            num--;
        }

        System.out.println(maxSum);
    }
}