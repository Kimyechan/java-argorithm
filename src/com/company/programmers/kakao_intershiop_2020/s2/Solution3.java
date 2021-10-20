package com.company.programmers.kakao_intershiop_2020.s2;

import java.util.*;

public class Solution3 {
    List<String> operands = new ArrayList<>();
    List<String> operators = new ArrayList<>();
    long maxMoney = Long.MIN_VALUE;

    public long solution(String expression) {
        initOperands(expression);
        initOperators(expression);

        boolean[] visited = new boolean[3];
        String[] opCandidates = {"*", "+", "-"};
        dfs(visited, opCandidates, new ArrayList<>());

        return maxMoney;
    }

    private void dfs(boolean[] visited, String[] opCandidates, List<String> orders) {
        if (orders.size() == 3) {
            List<String> operandTemps = new LinkedList<>(operands);
            List<String> operatorTemps = new LinkedList<>(operators);

            for (String order : orders) {
                int i = 0;
                while (i < operatorTemps.size()) {
                    if (!operatorTemps.get(i).equals(order)) {
                        i += 1;
                        continue;
                    }

                    long num1 = Long.parseLong(operandTemps.remove(i));
                    long num2 = Long.parseLong(operandTemps.remove(i));
                    long result = calcNumbers(num1, num2, operatorTemps.remove(i));

                    operandTemps.add(i, String.valueOf(result));
                }
            }

            maxMoney = Math.max(maxMoney, Math.abs(Long.parseLong(operandTemps.get(0))));

            return;
        }

        for (int i = 0; i < 3; i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            orders.add(opCandidates[i]);
            dfs(visited, opCandidates, orders);
            orders.remove(orders.size() - 1);
            visited[i] = false;
        }
    }

    private long calcNumbers(long num1, long num2, String op) {
        if (op.equals("+")) {
            return num1 + num2;
        } else if (op.equals("-")) {
            return num1 - num2;
        } else {
            return num1 * num2;
        }
    }


    private void initOperators(String expression) {
        for (char c : expression.toCharArray()) {
            if (c == '*' || c == '-' || c == '+') {
                operators.add(String.valueOf(c));
            }
        }
    }

    private void initOperands(String expression) {
        String[] operandSplits = expression.split("\\+|\\-|\\*");
        operands.addAll(Arrays.asList(operandSplits));
    }
}
