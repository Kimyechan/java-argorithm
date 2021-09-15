package com.company.baekjun.b2800;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static String str;
    static char[] expression;
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

        expression = new char[str.length()];
        for (int i = 0; i < str.length(); i++) {
            expression[i] = str.charAt(i);
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

    private static void combination(int idx, int count, List<Integer> selectList) {
        if (selectList.size() == count) {
            char[] expressionTemp = new char[str.length()];
            System.arraycopy(expression, 0, expressionTemp, 0, str.length());

            for (int openIdx : selectList) {
                int closeIdx = reverse[openIdx];
                expressionTemp[openIdx] = ' ';
                expressionTemp[closeIdx] = ' ';
            }

            StringBuilder sb = new StringBuilder();
            for (char c : expressionTemp) {
                if (c != ' ') {
                    sb.append(c);
                }
            }

            possibleSet.add(sb.toString());

            return;
        }

        for (int i = idx; i < openList.size(); i++) {
            selectList.add(openList.get(i));
            combination(i + 1, count, selectList);
            selectList.remove(selectList.size() - 1);
        }
    }
}