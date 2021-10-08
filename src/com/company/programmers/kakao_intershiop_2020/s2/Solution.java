package com.company.programmers.kakao_intershiop_2020.s2;

import java.util.*;

class Solution {
    long maxValue = 0;
    List<String> operandList = new ArrayList<>();
    List<String> operatorList = new ArrayList<>();
    Set<String> operatorSet = new HashSet<>();

    public long solution(String expression) {
        init(expression);

        boolean[] visited = new boolean[operatorSet.size()];
        List<String> candidateList = new ArrayList(operatorSet);

        dfs(visited, candidateList, new ArrayList<>());

        return maxValue;
    }

    public void dfs(boolean[] visited, List<String> candidateList, List<String> orderList) {
        if (orderList.size() == candidateList.size()) {
            List<String> operandLink = new LinkedList<>(operandList);
            List<String> operatorLink = new LinkedList<>(operatorList);

            for (String candidate : orderList) {
                int idx = 0;
                while (idx < operatorLink.size()) {
                    if (operatorLink.get(idx).equals(candidate)) {
                        long op1 = Long.parseLong(operandLink.get(idx));
                        operandLink.remove(idx);
                        long op2 = Long.parseLong(operandLink.get(idx));
                        operandLink.remove(idx);
                        operatorLink.remove(idx);
                        operandLink.add(idx, calc(op1, op2, candidate));
                    } else {
                        idx++;
                    }
                }
            }

            long result = Long.parseLong(operandLink.get(0));
            maxValue = Math.max(maxValue, Math.abs(result));
            return;
        }

        for (int i = 0; i < candidateList.size(); i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            orderList.add(candidateList.get(i));
            dfs(visited, candidateList, orderList);
            orderList.remove(orderList.size() - 1);
            visited[i] = false;
        }
    }

    public String calc(long op1, long op2, String operator) {
        long result = 0;
        if (operator.equals("*")) {
            result = op1 * op2;
        } else if (operator.equals("-")) {
            result = op1 - op2;
        } else {
            result = op1 + op2;
        }

        return String.valueOf(result);
    }

    public void init(String expression) {
        StringBuilder sb = new StringBuilder();
        for (char c : expression.toCharArray()) {
            if (Character.isDigit(c)) {
                sb.append(c);
            } else {
                operandList.add(sb.toString());
                operatorList.add(String.valueOf(c));
                operatorSet.add(String.valueOf(c));
                sb = new StringBuilder();
            }
        }
        operandList.add(sb.toString());
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println(solution.solution("100-200*300-500+20"));
        System.out.println(solution.solution("50*6-3*2"));
    }
}
