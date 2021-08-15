package com.company.baekjun.b2812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main3 {
    static int n, k;
    static int[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        k = Integer.parseInt(input[1]);

        numbers = new int[n];
        input = br.readLine().split("");
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(input[i]);
        }

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            if (stack.size() == 0) {
                stack.push(numbers[i]);
                continue;
            }

            while (!stack.isEmpty() && stack.peek() < numbers[i]) {
                if (k == 0) {
                    break;
                }
                stack.pop();
                k--;
            }

            stack.push(numbers[i]);
        }

        for (int i = 0; i < k; i++) {
            stack.pop();
        }

        StringBuilder sb = new StringBuilder();
        for (Integer number : stack) {
            sb.append(number);
        }

        System.out.println(sb);
    }
}