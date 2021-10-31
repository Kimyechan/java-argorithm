package com.company.leetcode.valid_palindrome;


class Solution {
    public boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isAlphabetic(s.charAt(i))) {
                sb.append(Character.toLowerCase(s.charAt(i)));
            } else if (Character.isDigit(s.charAt(i))) {
                sb.append(s.charAt(i));
            }
        }

        String alphabets = sb.toString();
        for (int i = 0; i < alphabets.length() / 2; i++) {
            if (alphabets.charAt(i) != alphabets.charAt(alphabets.length() - 1 - i)) {
                return false;
            }
        }

        return true;
    }
}