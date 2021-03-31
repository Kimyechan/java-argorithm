package com.company.baekjun.b11723;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int bitset = 0;
        StringBuilder sb = new StringBuilder("");

        for (int i = 0; i < n; i++) {
            String[] command = br.readLine().split(" ");
            int number;

            switch (command[0]) {
                case "add":
                    number = Integer.parseInt(command[1]);
                    bitset |= (1 << (number - 1));
                    break;
                case "remove":
                    number = Integer.parseInt(command[1]);
                    bitset &= ~(1 << (number - 1));
                    break;
                case "check":
                    number = Integer.parseInt(command[1]);
                    int temp = bitset & (1 << (number - 1));
                    if (temp != 0) {
                        sb.append(1 + "\n");
                    } else {
                        sb.append(0 + "\n");
                    }
                    break;
                case "toggle":
                    number = Integer.parseInt(command[1]);
                    if ((bitset & (1 << (number - 1))) > 0) {
                        bitset &= ~(1 << (number - 1));
                    } else {
                        bitset |= (1 << (number - 1));
                    }
                    break;
                case "all":
                    bitset |= (~0);
                    break;
                case "empty":
                    bitset &= 0;
                    break;
            }
        }
        System.out.println(sb);
    }
}
