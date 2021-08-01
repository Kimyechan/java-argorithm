package com.company.baekjun.b9935;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String target = br.readLine();
        String bomb = br.readLine();

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < target.length(); i++) {
            stack.push(target.charAt(i));
            if (stack.size() < bomb.length()) {
                continue;
            }

            boolean isBomb = true;
            for (int j = 0; j < bomb.length(); j++) {
                if (stack.get(stack.size() - bomb.length() + j) != bomb.charAt(j)) {
                    isBomb = false;
                    break;
                }
            }

            if (isBomb) {
                for (int j = 0; j < bomb.length(); j++) {
                    stack.pop();
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Character character : stack) {
            sb.append(character);
        }

        if (sb.toString().equals("")) {
            System.out.println("FRULA");
        } else {
            System.out.println(sb);
        }
    }
}
