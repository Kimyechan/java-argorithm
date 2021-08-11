package com.company.baekjun.b2812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    static int n, k;
    static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] row = br.readLine().split(" ");
        n = Integer.parseInt(row[0]);
        k = Integer.parseInt(row[1]);

        String nNumber = br.readLine();

        int popCount = 0;
        for (int i = 0; i < n; i++) {
            int num = nNumber.charAt(i) - '0';

            if (stack.size() == 0) {
                stack.push(num);
                continue;
            }

            while (stack.size() != 0 && stack.peek() < num) {
                if (popCount == k) {
                    break;
                }
                stack.pop();
                popCount++;
            }

            stack.push(num);
        }

        for (int i = 0; i < k - popCount; i++) {
            stack.pop();
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < stack.size(); i++) {
            sb.append(stack.get(i));
        }

        System.out.println(sb.toString());
    }
}