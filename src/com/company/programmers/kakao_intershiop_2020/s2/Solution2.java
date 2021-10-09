package com.company.programmers.kakao_intershiop_2020.s2;

import java.util.*;

public class Solution2 {
    List<Long> operands = new LinkedList<>();
    List<String> operators = new LinkedList<>();
    static long maxValue = 0;

    public long solution(String expression) {
        StringBuilder sb = new StringBuilder();
        for (char c : expression.toCharArray()) {
            if (Character.isDigit(c)) {
                sb.append(c);
            } else {
                operators.add(String.valueOf(c));
                operands.add(Long.parseLong(sb.toString()));
                sb = new StringBuilder();
            }
        }
        operands.add(Long.parseLong(sb.toString()));

        List<String> candList = new ArrayList<>();
        candList.add("*");
        candList.add("+");
        candList.add("-");
        boolean[] visited = new boolean[3];

        dfs(candList, visited, new ArrayList<>());

        return maxValue;
    }

    private void dfs(List<String> candList, boolean[] visited, List<String> orders) {
        if (orders.size() == 3) {
            List<Long> operandTemps = new LinkedList<>(operands);
            List<String> operatorTemps = new LinkedList<>(operators);

            for (String order : orders) {
                int i = 0;
                while(i < operatorTemps.size()) {
                    if (operatorTemps.get(i).equals(order)) {
                        String operator = operatorTemps.remove(i);
                        Long op1 = operandTemps.remove(i);
                        Long op2 = operandTemps.remove(i);

                        long result = calc(op1, op2, operator);
                        operandTemps.add(i, result);
                    } else {
                        i++;
                    }
                }
            }

            maxValue = Math.max(maxValue, Math.abs(operandTemps.get(0)));
            return;
        }

        for (int i = 0; i < 3; i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            orders.add(candList.get(i));
            dfs(candList, visited, orders);
            orders.remove(orders.size() - 1);
            visited[i] = false;
        }
    }

    private long calc(Long op1, Long op2, String operator) {
        if (operator.equals("+")) {
            return op1 + op2;
        } else if (operator.equals("-")) {
            return op1 - op2;
        } else {
            return op1 * op2;
        }
    }

    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        System.out.println(solution.solution("100-200*300-500+20"));
//        System.out.println(solution.solution("50*6-3*2"));
    }
}
