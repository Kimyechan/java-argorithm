package com.company.programmers.problem.수박수박수박;

class Solution {
    public String solution(int n) {
        StringBuilder answer = new StringBuilder();
        for (int i = 1; i < n + 1; i++) {
            if (i % 2 == 0) {
                answer.append("박");
            } else {
                answer.append("수");
            }
        }

        return answer.toString();
    }
}