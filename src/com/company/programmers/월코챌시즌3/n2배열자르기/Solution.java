package com.company.programmers.월코챌시즌3.n2배열자르기;

public class Solution {
    public int[] solution(int n, long left, long right) {
        int len = (int) (right - left + 1);
        int[] answer = new int[len];

        for (int i = 0; i < len; i++) {
            long pos = left + i;
            int div = (int) (pos / n);
            int mod = (int) (pos % n);

            int number = Math.max(div, mod);
            answer[i] = number + 1;
        }

        return answer;
    }
}
