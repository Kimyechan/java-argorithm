package com.company.baekjun.b18870;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder("");
        int n = Integer.parseInt(br.readLine());

        List<Integer> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        Set<Integer> set = new HashSet<>(list);
        List<Integer> newList = new ArrayList<>();
        newList.addAll(set);
        Collections.sort(newList);

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < newList.size(); i++) {
            map.put(newList.get(i), i);
        }
        for (Integer num : list) {
            sb.append(map.get(num)).append(" ");
        }
        System.out.println(sb);
    }
}
