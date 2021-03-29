package com.company.queue.요세푸스문제0;

import java.io.IOException;
import java.util.*;

public class Main {
    private static Queue<Integer> queue = new LinkedList<>();
    private static List<Integer> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        for (int i = 1; i < n + 1; i++) {
            queue.offer(i);
        }

        while (queue.size() != 0) {
            for (int i = 0; i < k - 1; i++) {
                queue.offer(queue.poll());
            }
            result.add(queue.poll());
        }

        StringBuilder answer = new StringBuilder("<");

        while (result.size() != 1) {
            answer.append(result.remove(0) + ", ");
        }

        answer.append(result.remove(0) + ">");

        System.out.println(answer);
    }
}
