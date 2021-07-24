package com.company.baekjun.b9935;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    private static Stack<Character> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String target = br.readLine();
        String bomb = br.readLine();

        String result = solution(target, bomb);

        System.out.println(result);
    }

    private static String solution(String target, String bomb) {
        char[] targetArray = target.toCharArray();
        char[] bombArray = bomb.toCharArray();

        for (char c : targetArray) {
            stack.push(c);

            if (stack.size() < bombArray.length) {
                continue;
            }

            Stack<Character> stackTemp = new Stack<>();
            for (int i = bombArray.length - 1; i >= 0; i--) {
                if (stack.peek() == bombArray[i]) {
                    stackTemp.push(stack.pop());
                } else {
                    break;
                }
            }

            if (stackTemp.size() != bombArray.length) {
                while (!stackTemp.isEmpty()) {
                    stack.push(stackTemp.pop());
                }
            }
        }

        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        if (result.toString().equals("")) {
            return "FRULA";
        }
        return result.reverse().toString();
    }
}
