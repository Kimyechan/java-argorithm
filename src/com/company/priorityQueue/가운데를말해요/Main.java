package com.company.priorityQueue.가운데를말해요;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> left = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> right = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());

            if (left.size() == right.size()) {
                left.offer(num);
            } else {
                right.offer(num);
            }

            if (right.size() != 0 && left.peek() > right.peek()) {
                int leftMax = left.poll();
                int rightMin = right.poll();
                left.offer(rightMin);
                right.offer(leftMax);
            }

            bw.write(left.peek() +"\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
