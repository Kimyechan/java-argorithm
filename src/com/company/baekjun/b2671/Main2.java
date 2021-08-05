package com.company.baekjun.b2671;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2 {
    static boolean checkSubmarine(String sound) {
        int soundLen = sound.length();
        int i = 0;

        while (i < soundLen) {
            if (sound.charAt(i) == '0') {
                if (i + 1 >= soundLen) {
                    return false;
                }
                if (sound.charAt(i + 1) != '1') {
                    return false;
                }
                i += 2;
            } else {
                if (i + 3 >= soundLen) {
                    return false;
                }
                if (!(sound.charAt(i + 1) == '0' && sound.charAt(i + 2) == '0')) {
                    return false;
                }
                i++;
                while (i < soundLen && sound.charAt(i) == '0') {
                    i++;
                }
                if (i >= soundLen) {
                    return false;
                }
                i++;
                while (i < soundLen && sound.charAt(i) == '1') {
                    if (i + 2 < soundLen && sound.charAt(i + 1) == '0' && sound.charAt(i + 2) == '0') {
                        break;
                    }
                    i++;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String sound = br.readLine();
        if (checkSubmarine(sound)) {
            System.out.println("SUBMARINE");
        } else {
            System.out.println("NOISE");
        }
    }
}
