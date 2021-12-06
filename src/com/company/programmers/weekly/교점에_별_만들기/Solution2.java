package com.company.programmers.weekly.교점에_별_만들기;

import java.util.ArrayList;
import java.util.List;

public class Solution2 {
    List<Spot> spotList = new ArrayList<>();
    int minX = Integer.MAX_VALUE;
    int maxX = Integer.MIN_VALUE;
    int minY = Integer.MAX_VALUE;
    int maxY = Integer.MIN_VALUE;

    static class Spot {
        int x;
        int y;

        public Spot(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public String[] solution(int[][] line) {
        String[] answer = {};
        int len = line.length;

        for (int i = 0; i < len; i++) {
            long A = line[i][0];
            long B = line[i][1];
            long E = line[i][2];
            for (int j = i + 1; j < len; j++) {
                long C = line[j][0];
                long D = line[j][1];
                long F = line[j][2];

                long ADBC = A * D - B * C;
                long BFED = B * F - E * D;
                long ECAF = E * C - A * F;

                if (ADBC == 0) {
                    continue;
                }

                if (BFED % ADBC == 0 && ECAF % ADBC == 0) {
                    int x = (int) (BFED / ADBC);
                    int y = (int) (ECAF / ADBC);

                    minX = Math.min(x, minX);
                    maxX = Math.max(x, maxX);
                    minY = Math.min(y, minY);
                    maxY = Math.max(y, maxY);

                    spotList.add(new Spot(x, y));
                }
            }
        }

        for (Spot spot : spotList) {
            spot.x = spot.x - minX;
            spot.y = maxY - spot.y;
        }

        int xLen = maxX - minX + 1;
        int yLen = maxY - minY + 1;
        char[][] board = new char[yLen][xLen];

        for (int i = 0; i < yLen; i++) {
            for (int j = 0; j < xLen; j++) {
                board[i][j] = '.';
            }
        }

        for (Spot spot : spotList) {
            board[spot.y][spot.x] = '*';
        }

        answer = new String[yLen];
        for (int i = 0; i < yLen; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < xLen; j++) {
                sb.append(board[i][j]);
            }
            answer[i] = sb.toString();
        }

        return answer;
    }
}
