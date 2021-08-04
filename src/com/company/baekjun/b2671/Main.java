package com.company.baekjun.b2671;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String sound = br.readLine();
        boolean isMatched = sound.matches("(100+1+|01)+");

        if (isMatched) {
            System.out.println("SUBMARINE");
        } else {
            System.out.println("NOISE");
        }
    }
}