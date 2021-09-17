package com.company.baekjun.b1038;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main3 {
    static List<Long> candidates = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        if (n < 10) {
            System.out.println(n);
        } else if (n > 1022) {
            System.out.println(-1);
        } else {
            for (int i = 0; i < 10; i++) {
                recursion(i);
            }
            Collections.sort(candidates);
            System.out.println(candidates.get(n));
        }
    }

    private static void recursion(long num) {
        candidates.add(num);

        long oneDigit = num % 10;
        for (int i = 0; i < oneDigit; i++) {
            recursion(num * 10 + i);
        }
    }
}