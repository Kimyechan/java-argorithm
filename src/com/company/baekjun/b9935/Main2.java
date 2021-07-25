package com.company.baekjun.b9935;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String a = br.readLine();
        String b = br.readLine();

        String result = solution(a, b);

        System.out.println(result);
    }

    private static String solution(String a, String b) {
        Stack<Character> stack = new Stack<>();

        char[] aArray = a.toCharArray();
        char[] bArray = b.toCharArray();

        for (int i = 0; i < a.length(); i++) {
            stack.push(aArray[i]);

            if (stack.size() < bArray.length) {
                continue;
            }

            Stack<Character> stackTemp = new Stack<>();
            for (int j = b.length() - 1; j >= 0; j--) {
                if (bArray[j] == stack.peek()) {
                    stackTemp.push(stack.pop());
                } else {
                    break;
                }
            }

            if (stackTemp.size() != b.length()) {
                while (!stackTemp.isEmpty()) {
                    stack.push(stackTemp.pop());
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        sb.reverse();
        String result = sb.toString();

        if (result.equals("")) {
            return "FRULA";
        }

        return result;
    }
}
