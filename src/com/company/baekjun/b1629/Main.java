package com.company.baekjun.b1629;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        long c = Long.parseLong(st.nextToken());

        long result = calcPow(a, b, c);

        System.out.println(result);
    }

    private static long calcPow(long a, long b, long c) {
        if (b == 1) {
            return a % c;
        }

        long temp = calcPow(a, b / 2, c) % c;
        if (b % 2 == 0) {
            return (temp * temp) % c;
        } else {
            return ((temp * temp) % c) * a % c;
        }
    }
}