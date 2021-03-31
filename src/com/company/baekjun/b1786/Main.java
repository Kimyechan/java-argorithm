package com.company.baekjun.b1786;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static int[] kmpTable;
    private static List<Integer> result = new ArrayList<>();
    private static Integer count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] t = br.readLine().split("");
        String[] p = br.readLine().split("");
        kmpTable = new int[p.length];

        kmp(t, p);

        System.out.println(count);
        for (Integer index : result) {
            System.out.print(index + " ");
        }
    }

    public static void makeKmpTable(String[] p) {
        int j = 0;
        for (int i = 1; i < p.length; i++) {
            while (j > 0 && !p[i].equals(p[j])) {
                j = kmpTable[j - 1];
            }
            if (p[i].equals(p[j])) {
                j += 1;
                kmpTable[i] = j;
            }
        }
    }

    public static void kmp(String[] t, String[] p) {
        makeKmpTable(p);
        int j = 0;
        for (int i = 0; i < t.length; i++) {
            while (j > 0 && !t[i].equals(p[j])) {
                j = kmpTable[j - 1];
            }
            if (t[i].equals(p[j])) {
                if (j == p.length - 1) {
                    count += 1;
                    result.add(i - p.length + 2);
                    j = kmpTable[j];
                } else {
                    j += 1;
                }
            }
        }
    }
}

/*
public class Main {
    static int[] pi;
    static char[] T;
    static char[] P;
    static int cnt;
    static StringBuilder str = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = br.readLine().toCharArray();
        P = br.readLine().toCharArray();
        pi = new int[P.length];

        //make pi
        int j = 0;
        for (int i = 1; i < P.length; i++) {

            while (j > 0 && P[i] != P[j])
            {
                j = pi[j - 1];
            }

            if (P[i] == P[j]) pi[i] = ++j;
        }

        // KMP
        j = 0;
        for (int i = 0; i < T.length; i++) {

            while (j > 0 && T[i] != P[j])
            {
                j = pi[j - 1];
            }

            if (T[i] == P[j]) ++j;

            if (j == P.length) {
                cnt++;
                str.append(i - (P.length - 1) + 1).append(" ");
                j = pi[j - 1];
            }
        }
        System.out.println(cnt);
        System.out.print(str);
    }
}*/
