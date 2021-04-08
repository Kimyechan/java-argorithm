package com.company.programmers.고득점Kit.dp.N으로표현;

import java.util.ArrayList;
import java.util.List;

class Solution {
    private List<List<Integer>> count = new ArrayList<>();
    public int solution(int N, int number) {
        int answer = 0;

        for (int i = 0; i < 9; i++) {
            count.add(new ArrayList<>());

            if (i == 0) {
                continue;
            }

            StringBuilder sb = new StringBuilder();
            sb.append(String.valueOf(N).repeat(i));
            count.get(i).add(Integer.valueOf(sb.toString()));
        }

        if (N == number) {
            answer = 1;
            return answer;
        }

        for (int i = 2; i < 9; i++) {
            for (int j = 1; j < i; j++) {
                for (Integer op1 : count.get(j)) {
                    for (Integer op2 : count.get(i - j)) {
                        count.get(i).add(op1 + op2);
                        count.get(i).add(op1 - op2);
                        count.get(i).add(op1 * op2);
                        if (op2 != 0) {
                            count.get(i).add((int)(op1 / op2));
                        }
                    }
                }
            }
            if (count.get(i).contains(number)) {
                answer = i;
                break;
            }
        }

        if (answer == 0) {
            answer = -1;
        }

        return answer;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println(solution.solution(5, 12));
        System.out.println(solution.solution(5, 5));
    }
}
