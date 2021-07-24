package com.company.baekjun.b5052;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());

            String[] phoneNumbers = new String[n];
            for (int j = 0; j < n; j++) {
                phoneNumbers[j] = br.readLine();
            }

            String result = solution(n, phoneNumbers);

            System.out.println(result);
        }
    }

    private static String solution(int n, String[] phoneNumbers) {
        Arrays.sort(phoneNumbers);

        String result = "YES";

        for (int i = 0; i < n - 1; i++) {
            if (phoneNumbers[i + 1].startsWith(phoneNumbers[i])) {
                result = "NO";
            }
        }

        return result;
    }
}
