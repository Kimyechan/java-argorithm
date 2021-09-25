package com.company.baekjun.b1038;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main4 {
    static List<String> numbers = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < 10; i++) {
            numbers.add(String.valueOf(i));
        }
        for (int i = 0; i < 10; i++) {
            makeNumbers(String.valueOf(i));
        }
        numbers.sort(Comparator.comparingLong(Long::parseLong));

        if (numbers.size() <= n) {
            System.out.println(-1);
        } else {
            System.out.println(numbers.get(n));
        }
    }

    private static void makeNumbers(String number) {
        if (number.length() >= 10) {
            return;
        }

        int lastDigit = number.charAt(number.length() - 1) - '0';
        for (int i = 0; i < lastDigit; i++) {
            String newNumber = number + i;
            numbers.add(newNumber);
            makeNumbers(newNumber);
        }
    }
}