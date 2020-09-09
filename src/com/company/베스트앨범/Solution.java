package com.company.베스트앨범;

import java.util.*;
class Node implements Comparable<Node> {
    String key;
    int value;
    int index;
    public Node(String key, int value, int index) {
        this.key = key;
        this.value = value;
        this.index = index;
    }
    public Node(String key, int value) {
        this.key = key;
        this.value = value;
    }
    @Override
    public int compareTo(Node o) {
        return -Integer.compare(value, o.value);
    }

    public String toString() {
        return "(" + key + ", " + value + ", " + index + ")";
    }
}

public class Solution {
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> sumMap = new Hashtable<>();
        List<Node> list = new LinkedList<>();
        for (int i = 0; i < genres.length; i++) {
            String key = genres[i];
            int value = plays[i];
            int currentValue = sumMap.getOrDefault(key, 0);
            sumMap.put(key, currentValue + value);
            list.add(new Node(key, value, i));
        }
        List<Node> sumList = new LinkedList<>();
        for (Map.Entry<String, Integer> entry: sumMap.entrySet()) {
            sumList.add(new Node(entry.getKey(), entry.getValue()));
        }
        Collections.sort(sumList);
        Collections.sort(list);
        List<Integer> bests = new ArrayList<>();
        while (!sumList.isEmpty()) {
            String key = sumList.remove(0).key;
            int num = 0;
            for (Node node: list) {
                if (node.key.equals(key)) {
                    bests.add(node.index);
                    num++;
                }
                if (num == 2) {
                    break;
                }
            }
        }
        int[] answer = new int[bests.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = bests.get(i);
        }
        return answer;
    }
}