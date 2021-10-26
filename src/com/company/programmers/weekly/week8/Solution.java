package com.company.programmers.weekly.week8;

class Solution {
    public int solution(int[][] sizes) {
        int x = 0;
        int y = 0;

        for (int[] size : sizes) {
            if (size[0] > size[1]) {
                x = Math.max(x, size[0]);
                y = Math.max(y, size[1]);
            } else {
                x = Math.max(x, size[1]);
                y = Math.max(y, size[0]);
            }
        }

        return x * y;
    }
}