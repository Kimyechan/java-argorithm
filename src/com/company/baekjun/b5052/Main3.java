package com.company.baekjun.b5052;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            String[] phoneNumbers = new String[n];
            for (int j = 0; j < n; j++) {
                phoneNumbers[j] = br.readLine();
            }

            String answer = solution(n, phoneNumbers);

            System.out.println(answer);
        }
    }

    private static String solution(int n, String[] phoneNumbers) {
        Arrays.sort(phoneNumbers);

        String answer = "YES";
        for (int i = 1; i < n; i++) {
            if (phoneNumbers[i].startsWith(phoneNumbers[i - 1])) {
                answer = "NO";
            }
        }

        return answer;
    }
}
