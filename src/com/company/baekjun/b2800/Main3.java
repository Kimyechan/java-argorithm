package com.company.baekjun.b2800;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main3 {
    static String str;
    static char[] strArray;
    static int[] reverse;
    static List<Integer> openIdxList = new ArrayList<>();
    static Set<String> possibleSet = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        str = br.readLine();
        reverse = new int[str.length()];
        strArray = str.toCharArray();

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                stack.push(i);
                openIdxList.add(i);
            }
            if (str.charAt(i) == ')') {
                reverse[stack.pop()] = i;
            }
        }

        for (int i = 1; i <= openIdxList.size(); i++) {
            findPossibleStr(i, 0, new ArrayList<>());
        }

        List<String> possibleList = new ArrayList<>(possibleSet);
        Collections.sort(possibleList);

        for (String str : possibleList) {
            System.out.println(str);
        }
    }

    private static void findPossibleStr(int size, int start, List<Integer> candidates) {
        if (candidates.size() == size) {
            for (Integer openIdx : candidates) {
                int closeIdx = reverse[openIdx];
                strArray[openIdx] = ' ';
                strArray[closeIdx] = ' ';
            }
            
            StringBuilder sb = new StringBuilder();
            for (char c : strArray) {
                if (c == ' ') {
                    continue;
                }
                sb.append(c);
            }
            possibleSet.add(sb.toString());

            for (Integer openIdx : candidates) {
                int closeIdx = reverse[openIdx];
                strArray[openIdx] = '(';
                strArray[closeIdx] = ')';
            }

            return;
        }

        for (int i = start; i < openIdxList.size(); i++) {
            candidates.add(openIdxList.get(i));
            findPossibleStr(size, i + 1, candidates);
            candidates.remove(candidates.size() - 1);
        }
    }
}