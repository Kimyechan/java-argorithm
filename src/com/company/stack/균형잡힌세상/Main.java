package com.company.stack.균형잡힌세상;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {
    private static List<Character> pList = new ArrayList<>();
    private static Stack<Character> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String line = br.readLine();

            if (line.equals(".")){
                break;
            }

            for (char c : line.toCharArray()) {
                if (c == '[' || c == ']' || c == '(' || c == ')') {
                    pList.add(c);
                }
            }

            for (Character p : pList) {
                if (stack.size() == 0) {
                    stack.push(p);
                } else {
                    if ((stack.peek() == '(' && p == ')') || (stack.peek() == '[' && p == ']')) {
                        stack.pop();
                    } else {
                        stack.push(p);
                    }
                }
            }

            if (stack.size() == 0) {
                System.out.println("yes");
            } else {
                System.out.println("no");
            }

            pList.clear();
            stack.clear();
        }
    }
}
