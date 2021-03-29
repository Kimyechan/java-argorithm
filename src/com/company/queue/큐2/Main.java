package com.company.queue.큐2;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    private static Deque<String> queue = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Integer n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String[] command = br.readLine().split(" ");
            if (command[0].equals("push")) {
                queue.offer(command[1]);
            } else if (command[0].equals("pop")) {
                if (queue.size() == 0 ){
                    bw.write(-1 + "\n");
                } else {
                    bw.write(queue.poll() + "\n");
                }
            } else if (command[0].equals("size")) {
                bw.write(queue.size() + "\n");
            } else if (command[0].equals("empty")) {
                if (queue.size() == 0) {
                    bw.write(1 + "\n");
                } else {
                    bw.write(0 + "\n");
                }
            } else if (command[0].equals("front")) {
                if (queue.size() == 0) {
                    bw.write(-1 + "\n");
                } else {
                    bw.write(queue.getFirst() + "\n");
                }
            } else if (command[0].equals("back")) {
                if (queue.size() == 0) {
                    bw.write(-1 + "\n");
                } else {
                    bw.write(queue.getLast() + "\n");
                }
            }
        }

        br.close();
        bw.close();
    }
}
