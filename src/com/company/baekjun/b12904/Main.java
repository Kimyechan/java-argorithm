package com.company.baekjun.b12904;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        String t = br.readLine();

        StringBuilder sbT = new StringBuilder(t);

        while (s.length() != sbT.length()) {
            if (sbT.charAt(sbT.length() - 1) == 'A') {
                sbT.deleteCharAt(sbT.length() - 1);
            } else {
                sbT.deleteCharAt(sbT.length() - 1);
                sbT.reverse();
            }
        }

        String newT = sbT.toString();
        if (s.equals(newT)) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
}
