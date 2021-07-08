package com.company.baekjun.b11729;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        hanoi(1, 3, 2, n);
    }

    public static void hanoi(int from, int to, int aux, int n) {
        if (n == 0) {
            return;
        }

        hanoi(from, aux, to, n - 1);
        System.out.println(from + " " + to);
        hanoi(aux, to, from, n - 1);
    }
}
