package com.company.programmers.problem.두정수사이의합;

class Solution {
    public long solution(int a, int b) {
        long answer = 0;

        int temp = 0;
        if (a > b) {
            temp = a;
            a = b;
            b = temp;
        }

        for (int i = a; i < b + 1; i++) {
            answer += i;
        }

        return answer;
    }
}
