package com.company.programmers.kakao2019_winter_intership.s1;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution {
    public int solution(int[][] board, int[] moves) {
        int rowLen = board.length;
        int colLen = board[0].length;

        List<Stack<Integer>> stacks = new ArrayList<>();
        for (int i = 0; i < colLen; i++) {
            stacks.add(new Stack<>());
        }

        for (int i = rowLen - 1; i >= 0; i--) {
            for (int j = 0; j < colLen; j++) {
                if (board[i][j] != 0) {
                    stacks.get(j).push(board[i][j]);
                }
            }
        }

        Stack<Integer> bucket = new Stack<>();
        int bombCount = 0;
        for (int move : moves) {
            Stack<Integer> line = stacks.get(move - 1);

            if (line.isEmpty()) {
                continue;
            }
            int doll = line.pop();
            if (!bucket.isEmpty() && doll == bucket.peek()) {
                bucket.pop();
                bombCount++;
            } else {
                bucket.push(doll);
            }
        }

        return bombCount * 2;
    }
}
