package com.company.programmers.problem.없는숫자더하기;

class Solution {
    public int solution(int[] numbers) {
        int answer = 0;
        boolean[] existed = new boolean[10];

        for (int number : numbers) {
            existed[number] = true;
        }

        for (int i = 0; i < 10; i++) {
            if (!existed[i]) {
                answer += i;
            }
        }

        return answer;
    }
}