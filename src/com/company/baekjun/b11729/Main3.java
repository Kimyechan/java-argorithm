package com.company.baekjun.b11729;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main3 {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        hanoi(1, 3, 2, n);

        System.out.println((int) Math.pow(2, n) - 1);
        System.out.println(sb.toString());
    }

    private static void hanoi(int from, int to, int sub, int n) {
        if (n == 0) {
            return;
        }

        hanoi(from, sub, to, n - 1);
        sb.append(from).append(" ").append(to).append("\n");
        hanoi(sub, to, from, n - 1);
    }
}
