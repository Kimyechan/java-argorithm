package com.company.leetcode.valid_parentheses;

import java.util.Stack;

public class ValidParentheses {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (stack.size() == 0) {
                stack.push(c);
                continue;
            }
            if (c == '('|| c == '{' || c == '[') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.peek() == '(') {
                    stack.pop();
                } else {
                    stack.push(')');
                    break;
                }
            } else if (c == ']') {
                if (stack.peek() == '[') {
                    stack.pop();
                } else {
                    stack.push(']');
                    break;
                }
            } else if (c == '}') {
                if (stack.peek() == '{') {
                    stack.pop();
                } else {
                    stack.push('}');
                    break;
                }
            }
        }

        return stack.size() == 0;
    }
}