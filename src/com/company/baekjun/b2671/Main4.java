package com.company.baekjun.b2671;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String sound = br.readLine();

        if (checkSubmarine2(sound)) {
            System.out.println("SUBMARINE");
        } else {
            System.out.println("NOISE");
        }
    }

    private static boolean checkSubmarine(String sound) {
        return sound.matches("(100+1+|01)+");
    }

    private static boolean checkSubmarine2(String sound) {
        int sLen = sound.length();
        int idx = 0;

        while (idx < sLen) {
            if (sound.charAt(idx) == '0') {
                if (idx + 1 >= sLen) {
                    return false;
                }
                idx += 1;
                if (sound.charAt(idx) != '1') {
                    return false;
                } else {
                    idx += 1;
                }
            } else {
                if (idx + 2 >= sLen) {
                    return false;
                }

                if (sound.charAt(idx + 1) != '0' || sound.charAt(idx + 2) != '0') {
                    return false;
                }

                idx += 1;
                while (idx < sLen && sound.charAt(idx) == '0') {
                    idx += 1;
                }

                if (idx >= sLen) {
                    return false;
                }
                idx += 1;
                while (idx < sLen && sound.charAt(idx) == '1') {
                    if (idx + 2 < sLen && sound.charAt(idx + 1) == '0' && sound.charAt(idx + 2) == '0') {
                        break;
                    }
                    idx += 1;
                }
            }
        }

        return true;
    }
}