package com.company.baekjun.b2671;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String sound = br.readLine();

        if (checkSubmarine(sound)) {
            System.out.println("SUBMARINE");
        } else {
            System.out.println("NOISE");
        }
    }

    private static boolean checkSubmarine(String sound) {
        int soundLen = sound.length();
        int j = 0;

        while (j < soundLen) {
            if (sound.charAt(j) == '0') {
                if (j + 1 >= soundLen) {
                    return false;
                }
                if (sound.charAt(j + 1) != '1') {
                    return false;
                }
                j += 2;
            } else {
                if (j + 3 >= soundLen) {
                    return false;
                }
                if (!(sound.charAt(j + 1) == '0' && sound.charAt(j + 2) == '0')) {
                    return false;
                }
                j += 1;
                while (j < soundLen && sound.charAt(j) == '0') {
                    j += 1;
                }
                if (j >= soundLen) {
                    return false;
                }
                j += 1;
                while (j < soundLen && sound.charAt(j) == '1') {
                    if (j + 2 < soundLen && sound.charAt(j + 1) == '0' && sound.charAt(j + 2) == '0') {
                        break;
                    }
                    j++;
                }
            }
        }

        return true;
    }

    private static boolean checkSubmarineByRegex(String sound) {
        return sound.matches("(100+1+|01)+");
    }
}