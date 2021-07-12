package com.company.baekjun.b11729;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2 {
    private static int count;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        moveHanoi(1, 3, 2, n);
        System.out.println(count);
        System.out.println(sb);
    }

    public static void moveHanoi(int from, int to, int sub, int n) {
        if (n == 0) {
            return;
        }

        moveHanoi(from, sub, to, n - 1);
        count += 1;
        sb.append(from).append(" ").append(to).append("\n");
        moveHanoi(sub, to, from, n - 1);
    }
}
