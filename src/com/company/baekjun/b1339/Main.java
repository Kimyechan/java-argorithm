package com.company.baekjun.b1339;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 잘못된 방법
// AC, AC, AA, CA, CA, CA -> 같은 자리수 동일하게 등잘할 때 뒤의 자리의 갯수 신경써야함
public class Main {
    static int n;
    static boolean[] visited = new boolean[26];
    static int[] digits = new int[26];
    static List<String> words = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            words.add(br.readLine());
        }

        words.sort((o1, o2) -> {
            if (o1.length() > o2.length()) {
                return -1;
            } else if (o1.length() < o2.length()) {
                return 1;
            } else {
                return o1.compareTo(o2);
            }
        });

        int maxLen = words.get(0).length();

        int digit = 9;
        for (int i = maxLen; i >= 1; i--) {
            List<String> candidates = new ArrayList<>();
            for (String word : words) {
                if (word.length() >= i) {
                    candidates.add(word);
                }
            }

            Map<Integer, Integer> countMap = new LinkedHashMap<>();
            for (String candidate : candidates) {
                char c = candidate.charAt(candidate.length() - i);
                countMap.put(c - 'A', countMap.getOrDefault(c - 'A', 0) + 1);
            }

            Map<Integer, Integer> sortedCountMap = sortCountMapByValue(countMap);

            for (Integer alphabetNum : sortedCountMap.keySet()) {
                if (!visited[alphabetNum]) {
                    digits[alphabetNum] = digit--;
                    visited[alphabetNum] = true;
                }
            }
        }

        int numberSum = 0;
        for (String word : words) {
            StringBuilder sb = new StringBuilder();
            for (char c : word.toCharArray()) {
                sb.append(digits[c - 'A']);
            }

            numberSum += Integer.parseInt(sb.toString());
        }

        System.out.println(numberSum);
    }

    private static LinkedHashMap<Integer, Integer> sortCountMapByValue(Map<Integer, Integer> countMap) {
        List<Map.Entry<Integer, Integer>> entries = new LinkedList<>(countMap.entrySet());
        entries.sort((o1, o2) -> o1.getValue().compareTo(o2.getValue()) * -1);

        LinkedHashMap<Integer, Integer> result = new LinkedHashMap<>();
        for (Map.Entry<Integer, Integer> entry : entries) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}
