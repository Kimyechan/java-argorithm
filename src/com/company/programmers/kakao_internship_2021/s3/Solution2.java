package com.company.programmers.kakao_internship_2021.s3;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Solution2 {
    List<Integer> table = new LinkedList<>();

    class Memory {
        int number;
        int position;

        public Memory(int number, int position) {
            this.number = number;
            this.position = position;
        }
    }

    public String solution(int n, int k, String[] cmd) {
        StringBuilder answer = new StringBuilder();
        int original = n;

        for (int i = 0; i < n; i++) {
            table.add(i);
        }

        Stack<Memory> stack = new Stack<>();
        int cur = k;
        for (String c : cmd) {
            String[] input = c.split(" ");
            if (input[0].equals("D")) {
                cur = moveDown(n, cur, input[1]);
            } else if (input[0].equals("U")) {
                cur = moveUp(n, cur, input[1]);
            } else if (input[0].equals("C")) {
                int number = table.remove(cur);
                n -= 1;
                if (cur == table.size()) {
                    cur -= 1;
                }
                stack.push(new Memory(number, cur));
            } else if (input[0].equals("Z")) {
                Memory memory = stack.pop();
                table.add(memory.position + 1, memory.number);
                n += 1;
            }
        }

        String[] sArray = new String[original];
        for (Integer t : table) {
            sArray[t] = "O";
        }

        for (int i = 0; i < original; i++) {
            if (sArray[i] == null) {
                answer.append("X");
            } else {
                answer.append("O");
            }
        }

        return answer.toString();
    }

    private int moveUp(int n, int cur, String s) {
        int move = Integer.parseInt(s);
        while (move != 0) {
            cur = (cur - 1 + n) % n;
            move -= 1;
        }

        return cur;
    }

    private int moveDown(int n, int cur, String s) {
        int move = Integer.parseInt(s);
        while (move != 0) {
            cur = (cur + 1) % n;
            move -= 1;
        }

        return cur;
    }

    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        System.out.println(solution.solution(8, 2, new String[]{"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"}));
//        System.out.println(solution.solution(8, 2, new String[]{"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"}));
    }
}
