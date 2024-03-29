package com.company.baekjun.b1662;

import java.util.Scanner;
import java.util.Stack;

public class Main {
    static String line;
    static int[] reverse;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        line = sc.next();
        Stack<Integer> stack = new Stack<>();
        reverse = new int[50];

        for(int i=0;i<line.length();i++) {
            if(line.charAt(i)=='(') {
                stack.add(i);
            }
            if(line.charAt(i)==')') {
                reverse[stack.pop()] = i;
            }
        }

        System.out.println(traversal(0,line.length()));
    }

    public static int traversal(int start, int end) {
        int lineLength=0;

        for(int i=start;i<end;i++) {
            if(line.charAt(i)=='(') {
                lineLength += (line.charAt(i - 1) - '0' ) * traversal(i + 1, reverse[i]) - 1;
                i = reverse[i];
            } else {
                lineLength++;
            }
        }
        return lineLength;
    }
}
