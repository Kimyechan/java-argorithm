package com.company.leetcode.excel_sheet_column_number;

class Solution {
    public int titleToNumber(String columnTitle) {
        int num = 0;
        int pos = 1;
        int len = columnTitle.length();

        for (int i = len - 1; i >= 0; i--) {
            int value = columnTitle.charAt(i) - 'A' + 1;
            num += value * pos;
            pos *= 26;
        }

        return num;
    }
}
