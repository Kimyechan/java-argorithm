package com.company.baekjun.b10872;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);

        int result = factorial(n);

        System.out.println(result);
    }

    public static int factorial(int n) {
        if (n == 0) {
            return 1;
        } else if (n == 1){
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }
}
