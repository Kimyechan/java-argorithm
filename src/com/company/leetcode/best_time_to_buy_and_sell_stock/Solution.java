package com.company.leetcode.best_time_to_buy_and_sell_stock;

class Solution {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        int max = 0;
        int profitMax = 0;

        for (int i = len - 1; i >= 0; i--) {
            max = Math.max(max, prices[i]);
            profitMax = Math.max(profitMax, max - prices[i]);
        }

        return profitMax;
    }
}
