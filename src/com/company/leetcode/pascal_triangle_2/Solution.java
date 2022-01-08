package com.company.leetcode.pascal_triangle_2;

import java.util.*;

class Solution {
    int[][] pascal;

    public List<Integer> getRow(int rowIndex) {
        pascal = new int[rowIndex][rowIndex];
        List<Integer> rowList = new ArrayList<>();

        for (int i = 0; i < rowIndex + 1; i++) {
            rowList.add(recursion(rowIndex, i));
        }

        return rowList;
    }

    public int recursion(int row, int col) {
        if (col == 0 || col == row) {
            return 1;
        }

        if (pascal[row - 1][col - 1] == 0) {
            pascal[row - 1][col - 1] = recursion(row - 1, col - 1);
        }

        if (pascal[row - 1][col] == 0 ) {
            pascal[row - 1][col] = recursion(row - 1, col);
        }

        return pascal[row - 1][col - 1] + pascal[row - 1][col];
    }
}