package com.company.programmers.월코챌2109.s2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution {
    public int[] solution(String[] grid) {
        int[] answer = {};

        int rowLen = grid.length;
        int colLen = grid[0].length();

        char[][] gridC = new char[rowLen][rowLen];
        for (int i = 0; i < rowLen; i++) {
            gridC[i] = grid[i].toCharArray();
        }
        boolean[][][] passed = new boolean[rowLen][colLen][4];
        int[] dx = {0, -1, 0, 1}; // 왼쪽 -1, 오른쪽 +1
        int[] dy = {-1, 0, 1, 0};

        List<Integer> routeCountList = new ArrayList<>();
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                for (int k = 0; k < 4; k++) {
                    int startX = i;
                    int startY = j;
                    int startD = k;

                    int count = 0;
                    while (!passed[startX][startY][startD]) {
                        passed[startX][startY][startD] = true;
                        count += 1;
                        if (gridC[startX][startY] == 'L') {
                            startD = (startD + 1 + 4) % 4;
                        } else if (gridC[startX][startY] == 'R') {
                            startD = (startD - 1 + 4) % 4;
                        }
                        startX = (startX + dx[startD] + rowLen) % rowLen;
                        startY = (startY + dy[startD] + colLen) % colLen;
                    }

                    if (count != 0 && startX == i && startY == j && startD == k) {
                        routeCountList.add(count);
                    }
                }
            }
        }

        Collections.sort(routeCountList);

        answer = new int[routeCountList.size()];
        for (int i = 0; i < routeCountList.size(); i++) {
            answer[i] = routeCountList.get(i);
        }

        return answer;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println(Arrays.toString(solution.solution(new String[]{"SL", "LR"})));
//        System.out.println(Arrays.toString(solution.solution(new String[]{"S"})));
        System.out.println(Arrays.toString(solution.solution(new String[]{"R", "R"})));
    }
}