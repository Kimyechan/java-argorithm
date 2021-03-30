package com.company.baekjun.b7662;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            TreeMap<Integer, Integer> countMap = new TreeMap<>();

            int k = Integer.parseInt(br.readLine());
            for (int j = 0; j < k; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String command = st.nextToken();
                Integer num = Integer.parseInt(st.nextToken());

                if (command.equals("I")) {
                    if (!countMap.containsKey(num)) {
                       countMap.put(num, 1);
                    } else {
                        countMap.put(num, countMap.get(num) + 1);
                    }

                    maxHeap.offer(num);
                    minHeap.offer(num);
                } else {
                    if (num == 1) {
                        calcEachNumCount(maxHeap, countMap);
                    } else {
                        calcEachNumCount(minHeap, countMap);
                    }
                }
            }

            if (countMap.isEmpty()) {
                System.out.println("EMPTY");
            } else {
                System.out.println(countMap.lastKey() + " " + countMap.firstKey());
            }
        }
    }

    private static void calcEachNumCount(PriorityQueue<Integer> heap, Map<Integer, Integer> countMap) {
        while (heap.size() != 0) {
            int num = heap.poll();
            if (countMap.containsKey(num)) {
                countMap.put(num, countMap.get(num) - 1);
                if (countMap.get(num) == 0) {
                    countMap.remove(num);
                }
                break;
            }
        }
    }
}
