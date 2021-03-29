package com.company.queue.회전하는큐;

import java.util.*;

public class Main {
    private static ArrayDeque<Integer> deque = new ArrayDeque<>();
    private static List<Integer> order = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        for (int i = 1; i < n + 1; i++) {
            deque.offer(i);
        }

        for (int i = 0; i < m; i++) {
            order.add(sc.nextInt());
        }

        int result = 0;

        for (Integer num : order) {
            ArrayDeque<Integer> left = deque.clone();
            ArrayDeque<Integer> right = deque.clone();

            int leftCount = 0;
            while (left.peek() != num) {
                left.offerLast(left.pollFirst());
                leftCount += 1;
            }
            left.poll();

            int rightCount = 0;
            while(right.peek() != num) {
                right.offerFirst(right.pollLast());
                rightCount += 1;
            }
            right.poll();

            if (leftCount < rightCount) {
                deque = left;
                result += leftCount;
            } else {
                deque = right;
                result += rightCount;
            }
        }

        System.out.println(result);
    }
}
