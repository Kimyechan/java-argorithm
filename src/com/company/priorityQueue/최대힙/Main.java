package com.company.priorityQueue.최대힙;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    private static PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            int x = scanner.nextInt();

            if (x == 0) {
                if (queue.size() == 0) {
                    System.out.println("0");
                } else {
                    System.out.println(queue.poll());
                }
            } else {
                queue.offer(x);
            }
        }
    }
}
