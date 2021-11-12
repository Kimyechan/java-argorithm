package com.company.programmers.weekly.교점에_별_만들기;

import java.util.*;

class Solution {
    List<Spot> spotList = new ArrayList<>();

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

        for (int[] li1 : line) {
            long A = li1[0];
            long B = li1[1];
            long E = li1[2];

            for (int [] li2 : line) {
                long C = li2[0];
                long D = li2[1];
                long F = li2[2];

                if (A * D - B * C == 0) {
                    continue;
                }

                long bfed = B * F - E * D;
                long adbc = A * D - B * C;
                long ecaf = E * C - A * F;

                if (bfed % adbc == 0 && ecaf % adbc == 0) {
                    spotList.add(new Spot((int)(bfed / adbc), (int)(ecaf / adbc)));
                }
            }
        }

        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        for (Spot spot : spotList) {
            minX = Math.min(minX, spot.x);
            minY = Math.min(minY, spot.y);
            maxX = Math.max(maxX, spot.x);
            maxY = Math.max(maxY, spot.y);
        }

        int xLen = maxX - minX + 1;
        int yLen = maxY - minY + 1;
        String[][] temps = new String[yLen][xLen];
        for (int i = 0; i < yLen; i++) {
            for (int j = 0; j < xLen; j++) {
                temps[i][j] = ".";
            }
        }


        for (Spot spot : spotList) {
            int x = spot.x - minX;
            int y = maxY - spot.y;

            temps[y][x] = "*";
        }

        answer = new String[yLen];
        for (int i = 0; i < yLen; i++) {
            answer[i] = "";
        }

        for (int i = 0; i < yLen; i++) {
            for (int j = 0; j < xLen; j++) {
                answer[i] += temps[i][j];
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.solution(new int[][]{{2, -1, 4}, {-2, -1, 4}, {0, -1, 1}, {5, -8, -12}, {5, 8, 12}})));
    }
}
