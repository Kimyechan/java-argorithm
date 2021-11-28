package com.company.programmers.problem.문자열을정수로바꾸기;

class Solution {
    public int solution(String s) {
        if (s.charAt(0) == '+') {
            return Integer.parseInt(s.substring(1));
        } else if (s.charAt(0) == '-') {
            return -1 * Integer.parseInt(s.substring(1));
        } else {
            return Integer.parseInt(s);
        }
    }
}
