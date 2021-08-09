package com.company.baekjun.b1339;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main4 {
    static int n;
    static int[] alphabets = new int[26];
    static List<String> words = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            words.add(br.readLine());
        }

        for (String word : words) {
            int wLen = word.length();
            int pos = (int) Math.pow(10, wLen - 1);
            for (int i = 0; i < wLen; i++) {
                alphabets[word.charAt(i) - 'A'] += pos;
                pos /= 10;
            }
        }

        Arrays.sort(alphabets);

        int maxSum = 0;
        int num = 9;
        for (int i = 0; i < 10; i++) {
            maxSum += alphabets[25 - i] * num;
            num--;
        }

        System.out.println(maxSum);
    }
}