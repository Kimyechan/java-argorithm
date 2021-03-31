package com.company.baekjun.b11723;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashSet<Integer> set = new HashSet<>();
        StringBuilder sb = new StringBuilder("");

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String[] command = br.readLine().split(" ");

            if (command[0].equals("add")) {
                set.add(Integer.parseInt(command[1]));
            } else if (command[0].equals("remove")) {
                set.remove(Integer.parseInt(command[1]));
            } else if (command[0].equals("check")) {
                if (set.contains(Integer.parseInt(command[1]))) {
                    sb.append(1 + "\n");
                } else {
                    sb.append(0 + "\n");
                }
            } else if (command[0].equals("toggle")) {
                if (set.contains(Integer.parseInt(command[1]))) {
                    set.remove(Integer.parseInt(command[1]));
                } else {
                    set.add(Integer.parseInt(command[1]));
                }
            } else if (command[0].equals("all")) {
                set.clear();
                set.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20));
            } else {
                set.clear();
            }
        }

        System.out.println(sb);
    }
}
