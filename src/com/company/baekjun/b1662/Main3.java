package com.company.baekjun.b1662;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main3 {
    static String str;
    static int[] reverse;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        str = br.readLine();
        reverse = new int[str.length()];

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
        int len = 0;
        for (int i = start; i < end; i++) {
            if (str.charAt(i) == '(') {
                len += (str.charAt(i - 1) - '0') * recursion(i + 1, reverse[i]) - 1;
                i = reverse[i];
            } else {
                len++;
            }
        }

        return len;
    }
}