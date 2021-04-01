package com.company.baekjun.b1918;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Main {
    private static Stack<Character> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder("");
        char[] exp = br.readLine().toCharArray();
        Map<Character, Integer> orderMap = new HashMap<>();

        orderMap.put('+', 2);
        orderMap.put('-', 2);
        orderMap.put('*', 1);
        orderMap.put('/', 1);
        orderMap.put('(', 0);
        orderMap.put(')', 0);

        for (char c : exp) {
            if (c == '+' || c == '-' || c == '*' || c == '/') {
                if (stack.size() == 0) {
                    stack.push(c);
                } else {
                    while (stack.size() != 0 && orderMap.get(stack.peek()) <= orderMap.get(c)) {
                        if (stack.peek() == '(') {
                            break;
                        }
                        sb.append(stack.pop());
                    }
                    stack.push(c);
                }
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (stack.peek() != '(') {
                    sb.append(stack.pop());
                }
                stack.pop();
            } else {
                sb.append(c);
            }
        }

        while (stack.size() != 0) {
            sb.append(stack.pop());
        }

        System.out.println(sb);
    }
}
