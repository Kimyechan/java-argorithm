package com.company.baekjun.b1038;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Queue<Long> queue = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            queue.offer((long) i);
        }

        int count = 0;
        long result = 0;
        while (!queue.isEmpty()) { // 1022
            if (count == n) {
                result = queue.poll();
                break;
            }
            count++;
            long number = queue.poll();
            long firstDigit = number % 10;
            for (int i = 0; i < firstDigit; i++) {
                queue.offer(number * 10 + i);
            }
        }

        if (n != 0 && result == 0) {
            result = -1;
        }
        System.out.println(result);
    }
}