package com.company.baekjun.b2800;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main2 {
    static String str;
    static int[] reverse = new int[200];
    static List<Integer> openList = new ArrayList<>();
    static Set<String> possibleSet = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        str = br.readLine();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                stack.push(i);
                openList.add(i);
            }
            if (str.charAt(i) == ')') {
                reverse[stack.pop()] = i;
            }
        }

        for (int i = 1; i <= openList.size(); i++) {
            combination(0, i, new ArrayList<>());
        }

        List<String> possibleList = new ArrayList<>(possibleSet);
        Collections.sort(possibleList);

        for (String s : possibleList) {
            System.out.println(s);
        }
    }

    private static void combination(int start, int size, List<Integer> candidates) {
        if (candidates.size() == size) {
            char[] strChar = new char[str.length()];
            for (int i = 0; i < str.length(); i++) {
                strChar[i] = str.charAt(i);
            }

            for (int i = 0; i < candidates.size(); i++) {
                int openIdx = candidates.get(i);
                int closeIdx = reverse[openIdx];
                strChar[openIdx] = ' ';
                strChar[closeIdx] = ' ';
            }

            StringBuilder sb = new StringBuilder();
            for (char c : strChar) {
                if (c != ' ') {
                    sb.append(c);
                }
            }

            possibleSet.add(sb.toString());
            return;
        }

        for (int i = start; i < openList.size(); i++) {
            candidates.add(openList.get(i));
            combination(i + 1, size, candidates);
            candidates.remove(candidates.size() - 1);
        }
    }
}
