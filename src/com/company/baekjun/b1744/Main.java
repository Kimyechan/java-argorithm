package com.company.baekjun.b1744;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    static int n;
    static List<Integer> numbers = new ArrayList<>();
    static List<Integer> minus = new ArrayList<>();
    static List<Integer> plus = new ArrayList<>();
    static boolean isZeroExisted;
    static boolean isMinusOdd;
    static boolean isPlusOdd;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers.add(Integer.parseInt(br.readLine()));
        }

        for (Integer num : numbers) {
            if (num < 0) {
                minus.add(num);
            } else if (num > 0) {
                plus.add(num);
            } else {
                isZeroExisted = true;
            }
        }

        Collections.sort(minus);
        plus.sort(Collections.reverseOrder());

        isMinusOdd = minus.size() % 2 != 0;
        isPlusOdd = plus.size() % 2 != 0;

        int sum = 0;
        if (minus.size() != 0) {
            if (isMinusOdd) {
                for (int i = 0; i < minus.size() - 2; i = i + 2) {
                    sum += minus.get(i) * minus.get(i + 1);
                }
                if (!isZeroExisted) {
                    sum += minus.get(minus.size() - 1);
                }
            } else {
                for (int i = 0; i < minus.size() - 1; i = i + 2) {
                    sum += minus.get(i) * minus.get(i + 1);
                }
            }
        }

        if (plus.size() != 0) {
            if (isPlusOdd) {
                for (int i = 0; i < plus.size() - 2; i = i + 2) {
                    if (plus.get(i) != 1 && plus.get(i + 1) != 1) {
                        sum += plus.get(i) * plus.get(i + 1);
                    } else {
                        sum += plus.get(i) + plus.get(i + 1);
                    }
                }
                sum += plus.get(plus.size() - 1);
            } else {
                for (int i = 0; i < plus.size() - 1; i = i + 2) {
                    if (plus.get(i) != 1 && plus.get(i + 1) != 1) {
                        sum += plus.get(i) * plus.get(i + 1);
                    } else {
                        sum += plus.get(i) + plus.get(i + 1);
                    }
                }
            }
        }

        System.out.println(sum);
    }
}