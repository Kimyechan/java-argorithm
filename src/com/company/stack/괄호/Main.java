package com.company.stack.괄호;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    private static Stack<String> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            String[] ps = br.readLine().split("");

            for (String p : ps) {
                if (stack.size() == 0) {
                    stack.push(p);
                } else {
                    if (stack.peek().equals("(") && p.equals(")")) {
                        stack.pop();
                    } else {
                        stack.push(p);
                    }
                }
            }

            if (stack.size() == 0) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
            stack.clear();
        }

    }
}
