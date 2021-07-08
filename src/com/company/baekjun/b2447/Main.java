package com.company.baekjun.b2447;

import java.io.*;

//public class Main {
//    private static String[][] star;
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//
//        String[] input = br.readLine().split(" ");
//        int n = Integer.parseInt(input[0]);
//
//        star = new String[n][n];
//
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                star[i][j] = "*";
//            }
//        }
//
//        pickBlank(0, 0, n);
//
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                bw.write(star[i][j]);
//            }
//            bw.write("\n");
//        }
//
//        bw.flush();
//        bw.close();
//        br.close();
//    }
//
//    public static void pickBlank(int x, int y, int n) {
//        if (n == 1) {
//            return;
//        }
//
//        pickBlank(x, y, n / 3);
//        pickBlank(x, y + n / 3, n / 3);
//        pickBlank(x, y + 2 * n / 3, n / 3);
//
//        pickBlank(x + n / 3, y, n / 3);
//        for (int i = x + n / 3; i < x + 2 * n / 3; i++) {
//            for (int j = y + n / 3; j < y + 2 * n / 3; j++) {
//                star[i][j] = " ";
//            }
//        }
//        pickBlank(x + n / 3, y + 2 * n / 3, n / 3);
//
//        pickBlank(x + 2 * n / 3, y, n / 3);
//        pickBlank(x + 2 * n / 3, y + n / 3, n / 3);
//        pickBlank(x + 2 * n / 3, y + 2 * n / 3, n / 3);
//    }
//}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int [][] arr = new int [n][n];
        StringBuilder sb = new StringBuilder();

        stars(arr, 0, 0, n);

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(arr[i][j] == 1) {
                    sb.append("*");
                } else {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static void stars(int[][] arr, int x, int y, int n) {
        if(n == 1) {
            arr[x][y] = 1;
            return;
        }
        for(int i = x; i < n+x; i += n/3) {
            for(int j = y; j < n+y; j += n/3) {
                if(i == n/3 + x && j == n/3 + y) continue;
                stars(arr, i, j, n/3);
            }
        }
    }
}

