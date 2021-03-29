package com.company.stack.스택;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    private static Stack<String> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String[] commands = br.readLine().split(" ");

            if (commands[0].equals("push")) {
                stack.push(commands[1]);
            } else if (commands[0].equals("pop")) {
                if (stack.size() == 0) {
                    System.out.println(-1);
                } else {
                    System.out.println(stack.pop());
                }
            } else if (commands[0].equals("size")) {
                System.out.println(stack.size());
            } else if (commands[0].equals("empty")) {
                if (stack.size() == 0) {
                    System.out.println(1);
                } else {
                    System.out.println(0);
                }
            } else {
                if (stack.size() == 0) {
                    System.out.println(-1);
                } else {
                    System.out.println(stack.peek());
                }
            }
        }
    }
}
