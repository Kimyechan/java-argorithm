package com.company.baekjun.b17609;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            String s = br.readLine();

            boolean isPalindrome = checkPalindrome(s);

            if (isPalindrome) {
                System.out.println(0);
                continue;
            }

            boolean isPseudoLeft = checkPseudo(s, true);
            boolean isPseudoRight = checkPseudo(s, false);

            if (isPseudoLeft || isPseudoRight) {
                System.out.println(1);
            } else {
                System.out.println(2);
            }
        }
    }

    private static boolean checkPalindrome(String s) {
        boolean isPalindrome = true;
        for (int j = 0; j < s.length() / 2; j++) {
            if (s.charAt(j) != s.charAt(s.length() - 1 - j)) {
                isPalindrome = false;
                break;
            }
        }
        return isPalindrome;
    }

    private static boolean checkPseudo(String s, boolean isLeft) {
        boolean isPseudo = true;

        int count = 0;
        int left = 0;
        int right = s.length() - 1;

        while (left <= right) {
            if (s.charAt(left) == s.charAt(right)) {
                left += 1;
                right -= 1;
            } else {
                if (count == 1) {
                    isPseudo = false;
                    break;
                }

                count += 1;
                if (isLeft) {
                    left += 1;
                } else {
                    right -= 1;
                }
            }
        }

        return isPseudo;
    }
}
