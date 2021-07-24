package com.company.baekjun.b1062;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int maxMatchCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);

        String[] words = new String[n];
        for (int i = 0; i < n; i++) {
            words[i] = br.readLine();
        }

        System.out.println(solution(n, k, words));
    }

    private static int solution(int n, int k, String[] words) {
        if (k < 5) {
            return 0;
        } else if (k == 26) {
            return n;
        }

        String[] wordFragments = sliceWords(n, words);
        long visitedBit = initializeVisited();

        combination(k - 5, 0, wordFragments, visitedBit);

        return maxMatchCount;
    }

    private static void combination(int matchCount, int start, String[] wordFragments, long visitedBit) {
        if (matchCount == 0) {
            int count = 0;
            for (int i = 0; i < wordFragments.length; i++) {
                boolean isValid = true;
                for (int j = 0; j < wordFragments[i].length(); j++) {
                    if ((visitedBit & (1L << (wordFragments[i].charAt(j) - 'a'))) == 0) {
                        isValid = false;
                        break;
                    }
                }

                if (isValid) {
                    count += 1;
                }
            }

            maxMatchCount = Math.max(maxMatchCount, count);
            return;
        }

        for (int i = start; i < 26; i++) {
            if ((visitedBit & (1L << i)) != 0) {
                continue;
            }
            combination(matchCount -  1, i + 1, wordFragments, visitedBit | (1L << i));
        }
    }

    private static long initializeVisited() {
        long flag = 0;
        for (char c : "antic".toCharArray()) {
            flag = flag | (1L << (c - 'a'));
        }
        return flag;
    }

    private static String[] sliceWords(int n, String[] words) {
        String[] sliceWords = new String[n];
        for (int i = 0; i < n; i++) {
            sliceWords[i] = words[i].substring(4, words[i].length() - 4);
        }

        return sliceWords;
    }
}
