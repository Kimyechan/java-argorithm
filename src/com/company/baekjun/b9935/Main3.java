package com.company.baekjun.b9935;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main3 {
    private static Stack<Character> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String target = br.readLine();
        String bomb = br.readLine();

        String result = solution(target, bomb);

        System.out.println(result);
    }

    private static String solution(String target, String bomb) {
        for (int i = 0; i < target.length(); i++) {
            stack.push(target.charAt(i));

            if (stack.size() < bomb.length()) {
                continue;
            }

            boolean isMatched = true;
            for (int j = 0; j < bomb.length(); j++) {
                if (stack.get(stack.size() - bomb.length() + j) != bomb.charAt(j)) {
                    isMatched = false;
                    break;
                }
            }

            if (isMatched) {
                for (int j = 0; j < bomb.length(); j++) {
                    stack.pop();
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
