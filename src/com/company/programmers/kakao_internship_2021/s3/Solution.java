package com.company.programmers.kakao_internship_2021.s3;

import java.util.Stack;

public class Solution {
    int[] table;

    public String solution(int n, int k, String[] cmd) {
        StringBuilder answer = new StringBuilder();

        table = new int[n];

        Stack<Integer> stack = new Stack<>();
        int cur = k;
        for (String c : cmd) {
            String[] input = c.split(" ");
            if (input[0].equals("D")) {
                cur = moveDown(n, cur, input[1]);
            } else if (input[0].equals("U")) {
                cur = moveUp(n, cur, input[1]);
            } else if (input[0].equals("C")) {
                table[cur] = 1;
                stack.push(cur);

                boolean isLast = true;
                int next = cur;
                while (next != n - 1) {
                    next += 1;
                    if (table[next] == 0) {
                        isLast = false;
                        break;
                    }
                }

                if (isLast) {
                    int prev = cur;
                    while (prev != 0) {
                        prev -= 1;
                        if (table[prev] == 0) {
                            cur = prev;
                            break;
                        }
                    }
                } else {
                    cur = next;
                }
            } else if (input[0].equals("Z")){
                int backIdx = stack.pop();
                table[backIdx] = 0;
            }
        }

        for (int t : table) {
            if (t == 0) {
                answer.append("O");
            } else {
                answer.append("X");
            }
        }

        return answer.toString();
    }

    private int moveUp(int n, int cur, String s) {
        int move = Integer.parseInt(s);
        while (move != 0) {
            cur = (cur - 1 + n) % n;
            if (table[cur] == 0) {
                move -= 1;
            }
        }
        return cur;
    }

    private int moveDown(int n, int cur, String s) {
        int move = Integer.parseInt(s);
        while (move != 0) {
            cur = (cur + 1) % n;
            if (table[cur] == 0) {
                move -= 1;
            }
        }

        return cur;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(8, 2, new String[]{"D 2","C","U 3","C","D 4","C","U 2","Z","Z"}));
//        System.out.println(solution.solution(8, 2, new String[]{"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"}));
    }
}
