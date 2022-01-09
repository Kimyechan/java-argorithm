package com.company.leetcode.k_th_symbol_in_grammar;

class Solution {
    public int kthGrammar(int n, int k) {
        if (n == 1) {
            return 0;
        }

        if (k % 2 == 0) {
            if (kthGrammar(n - 1, k / 2) == 0) {
                return 1;
            } else {
                return 0;
            }
        } else {
            if (kthGrammar(n - 1, (k + 1) / 2) == 0) {
                return 0;
            } else {
                return 1;
            }
        }
    }
}
