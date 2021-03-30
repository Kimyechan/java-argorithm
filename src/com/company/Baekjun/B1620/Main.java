package com.company.Baekjun.B1620;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Map<Integer, String> numMap = new HashMap<>();
        Map<String, Integer> nameMap = new HashMap<>();

        for (int i = 1; i < n + 1; i++) {
            String name = br.readLine();
            numMap.put(i, name);
            nameMap.put(name, i);
        }

        for (int i = 0; i < m; i++) {
            String command = br.readLine();
//            command.chars().allMatch(Character::isDigit);
            try {
                Integer num = Integer.parseInt(command);
                System.out.println(numMap.get(num));
            } catch (Exception e) {
                System.out.println(nameMap.get(command));
            }
        }
    }
}
