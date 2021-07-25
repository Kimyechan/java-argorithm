package com.company.baekjun.b1062;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2 {
    private static int maxCount = 0;

    private static int solution(int n, int k, String[] words) {
        long flag = 0;
        for (char c : "antic".toCharArray()) {
            flag |= (1L << (c - 'a'));
        }

        String[] wordFragments = new String[n];
        for (int i = 0; i < n; i++) {
            wordFragments[i] = words[i].substring(4, words[i].length() - 4);
        }

        combination(k - 5, 0, wordFragments, flag);

        return maxCount;
    }

    private static void combination(int remainCount, int start, String[] wordFragments, long flag) {
        if (remainCount == 0) {
            int count = 0;
            for (String wordFragment : wordFragments) {
                boolean isPossible = true;
                for (char c : wordFragment.toCharArray()) {
                    if ((flag & (1L << (c - 'a'))) == 0) {
                        isPossible = false;
                        break;
                    }
                }

                if (isPossible) {
                    count += 1;
                }
            }

            maxCount = Math.max(maxCount, count);
            return;
        }

        for (int i = start; i < 26; i++) {
            if ((flag & (1L << i)) != 0) {
                continue;
            }

            long newFlag = flag | (1L << i);
            combination(remainCount - 1, i + 1, wordFragments, newFlag);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);

        String[] words = new String[n];
        for (int i = 0; i < n; i++) {
            words[i] = br.readLine();
        }

        int answer = solution(n, k, words);

        System.out.println(answer);
    }
}
