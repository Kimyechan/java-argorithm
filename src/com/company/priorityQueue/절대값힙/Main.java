package com.company.priorityQueue.절대값힙;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((num1, num2) -> {
            int abs1 = Math.abs(num1);
            int abs2 = Math.abs(num2);

            if (abs1 == abs2) {
                return num1 > num2 ? 1 : -1;
            } else {
                return abs1 - abs2;
            }
        });

        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(br.readLine());

            if (x == 0) {
                if (priorityQueue.size() == 0) {
                    System.out.println("0");
                } else {
                    System.out.println(priorityQueue.poll());
                }
            } else {
                priorityQueue.offer(x);
            }
        }
    }
}
