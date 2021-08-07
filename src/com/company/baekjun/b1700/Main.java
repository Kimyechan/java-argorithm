package com.company.baekjun.b1700;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int n;
    static int k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] row = br.readLine().split(" ");
        n = Integer.parseInt(row[0]);
        k = Integer.parseInt(row[1]);

        int[] orders = new int[k];
        row = br.readLine().split(" ");
        for (int i = 0; i < k; i++) {
            orders[i] = Integer.parseInt(row[i]);
        }

        boolean[] used = new boolean[101];
        int put = 0;
        int ans = 0;

        for (int i = 0; i < k; i++) {
            if (used[orders[i]]) {
                continue;
            }

            if (put < n) {
                used[orders[i]] = true;
                put++;
                continue;
            }

            List<Integer> afterUsingList = new ArrayList<>();
            for (int j = i; j < k; j++) {
                if (used[orders[j]] && !afterUsingList.contains(orders[j])) {
                    afterUsingList.add(orders[j]);
                }
            }

            if (afterUsingList.size() != n) {
                for (int j = 0; j < used.length; j++) {
                    if (used[j] && !afterUsingList.contains(j)) {
                        used[j] = false;
                        break;
                    }
                }
            } else {
                int remove = afterUsingList.get(afterUsingList.size() - 1);
                used[remove] = false;
            }

            used[orders[i]] = true;
            ans++;
        }

        System.out.println(ans);
    }
}