package com.company.baekjun.b1662;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main4 {
    static int[] reverse;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String strCompress = br.readLine();
        reverse = new int[strCompress.length()];

        initReverseArray(strCompress);

        int decompressLen = calcDecompressLength(0, strCompress.length(), strCompress);

        System.out.println(decompressLen);
    }

    private static int calcDecompressLength(int start, int end, String str) {
        int len = 0;
        for (int i = start; i < end; i++) {
            if (str.charAt(i) == '(') {
                int repeatCount = str.charAt(i - 1) - '0';
                len += repeatCount * calcDecompressLength(i + 1, reverse[i], str) - 1;
                i = reverse[i];
            } else {
                len++;
            }
        }

        return len;
    }

    private static void initReverseArray(String strCompress) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < strCompress.length(); i++) {
            if (strCompress.charAt(i) == '(') {
                stack.push(i);
            }
            if (strCompress.charAt(i) == ')') {
                reverse[stack.pop()] = i;
            }
        }
    }
}