package com.company.baekjun.b4256;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main3 {
    static List<Integer> preList;
    static List<Integer> inList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            String[] input = br.readLine().split(" ");
            preList = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                preList.add(Integer.parseInt(input[j]));
            }

            input = br.readLine().split(" ");
            inList = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                inList.add(Integer.parseInt(input[j]));
            }

            StringBuilder sb = new StringBuilder();
            postOrder(0, n, 0, sb);

            System.out.println(sb.toString());
        }
    }

    private static void postOrder(int start, int end, int rootIdx, StringBuilder sb) {
        if (rootIdx >= preList.size()) {
            return;
        }

        int root = preList.get(rootIdx);
        for (int i = start; i < end; i++) {
            if (root == inList.get(i)) {
                postOrder(start, i, rootIdx + 1, sb);
                postOrder(i + 1, end, rootIdx + i - start + 1, sb);
                sb.append(root).append(" ");
                break;
            }
        }
    }
}