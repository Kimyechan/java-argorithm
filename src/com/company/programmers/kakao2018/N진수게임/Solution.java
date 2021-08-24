package com.company.programmers.kakao2018.N진수게임;

public class Solution {
    static String[] digit = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};

    public String solution(int n, int t, int m, int p) {
        StringBuilder answer = new StringBuilder();
        StringBuilder digitLine = new StringBuilder("0");
        for (int i = 1; i < t * m; i++) {
            digitLine.append(changeToNDigit(n, i));
        }

        int idx = p - 1;
        for (int i = 0; i < t; i++) {
            answer.append(digitLine.charAt(idx));
            idx += m;
        }

        return answer.toString();
    }

    private String changeToNDigit(int n, int num) {
        if (num == 0) {
            return "";
        }

        int div = num / n;
        int mod = num % n;

        return changeToNDigit(n, div) + digit[mod];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(2, 4, 2, 1));
    }
}