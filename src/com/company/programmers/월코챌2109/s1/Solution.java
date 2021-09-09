package com.company.programmers.월코챌2109.s1;

class Solution {
    public int solution(int[] numbers) {
        int answer = 0;

        for (int i = 0; i < 10; i++) {
            boolean isInclude = false;
            for (int number : numbers) {
                if (number == i) {
                    isInclude = true;
                    break;
                }
            }
            if (!isInclude) {
                answer += i;
            }
        }

        return answer;
    }
}