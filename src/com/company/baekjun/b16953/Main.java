package com.company.baekjun.b16953;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] row = br.readLine().split(" ");
        int a = Integer.parseInt(row[0]);
        int b = Integer.parseInt(row[1]);

        int count = 0;
        boolean isValid = false;

        while (true) {
            if (b % 2 == 0) {
                b = b / 2;
            } else if (b % 2 == 1){
                String bString = String.valueOf(b);
                if (bString.charAt(bString.length() - 1) == '1') {
                    bString = bString.substring(0, bString.length() - 1);
                    b = Integer.parseInt(bString);
                } else {
                    break;
                }
            }
            count += 1;

            if (a == b) {
                isValid = true;
                break;
            }

            if (a > b) {
                break;
            }
        }

        if (isValid) {
            System.out.println(count + 1);
        } else {
            System.out.println(-1);
        }
    }
}