package com.company.baekjun.b1662;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main2 {
    static String str;
    static int[] reverse = new int[50];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        str = br.readLine();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                stack.push(i);
            }
            if (str.charAt(i) == ')') {
                reverse[stack.pop()] = i;
            }
        }

        int len = recursion(0, str.length());
        System.out.println(len);
    }

    private static int recursion(int start, int end) {
        int length = 0;
        int idx = start;
        while (idx < end) {
            if (str.charAt(idx) == '(') {
                length += (str.charAt(idx - 1) - '0') * recursion(idx + 1, reverse[idx]) - 1;
                idx = reverse[idx];
            } else {
                length++;
            }
            idx++;
        }

        return length;
    }
}