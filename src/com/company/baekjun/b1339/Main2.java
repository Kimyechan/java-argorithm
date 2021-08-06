package com.company.baekjun.b1339;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간 초과
public class Main2 {
    static int n;
    static List<String> words = new ArrayList<>();
    static List<Character> alphabetList = new ArrayList<>();
    static boolean[] used = new boolean[10];
    static int maxSum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        Set<Character> alphabetSet = new HashSet<>();
        for (int i = 0; i < n; i++) {
            String word = br.readLine();
            words.add(word);
            for (char c : word.toCharArray()) {
                alphabetSet.add(c);
            }
        }

        alphabetList.addAll(alphabetSet);

        calcMaxSum(new ArrayList<>());

        System.out.println(maxSum);
    }

    private static void calcMaxSum(List<Integer> alphabetNums) {
        if (alphabetNums.size() == alphabetList.size()) {
            Map<Character, Integer> alphabetNumMap = new HashMap<>();
            for (int i = 0; i < alphabetNums.size(); i++) {
                alphabetNumMap.put(alphabetList.get(i), alphabetNums.get(i));
            }

            int sum = 0;
            for (String word : words) {
                StringBuilder sb = new StringBuilder();
                for (char c : word.toCharArray()) {
                    sb.append(alphabetNumMap.get(c));
                }
                sum += Integer.parseInt(sb.toString());
            }

            maxSum = Math.max(maxSum, sum);
            return;
        }

        for (int i = 0; i < 10; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            alphabetNums.add(i);
            calcMaxSum(alphabetNums);
            alphabetNums.remove(alphabetNums.size() - 1);
            used[i] = false;
        }
    }
}