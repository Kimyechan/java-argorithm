package com.company.baekjun.b1700;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main2 {
    static int n, k;
    static Set<Integer> plugSet = new HashSet<>();
    static int[] orders;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] row = br.readLine().split(" ");
        n = Integer.parseInt(row[0]);
        k = Integer.parseInt(row[1]);

        orders = new int[k];
        row = br.readLine().split(" ");
        for (int i = 0; i < k; i++) {
            orders[i] = Integer.parseInt(row[i]);
        }

        int minPopCount = 0;
        for (int i = 0; i < k; i++) {
            Integer plug = orders[i];

            if (plugSet.contains(plug)) {
                continue;
            }

            if (plugSet.size() < n) {
                plugSet.add(plug);
            } else {
                List<Integer> afterOrders = new ArrayList<>();
                for (int j = i + 1; j < k; j++) {
                    Integer candidate = orders[j];
                    if (plugSet.contains(candidate) && !afterOrders.contains(candidate)) {
                        afterOrders.add(candidate);
                    }
                }

                if (afterOrders.size() != n) {
                    for (Integer number : plugSet) {
                        if (!afterOrders.contains(number)) {
                            plugSet.remove(number);
                            break;
                        }
                    }
                } else {
                    Integer pop = afterOrders.get(afterOrders.size() - 1);
                    plugSet.remove(pop);
                }

                plugSet.add(plug);
                minPopCount += 1;
            }
        }

        System.out.println(minPopCount);
    }
}