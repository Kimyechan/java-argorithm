package com.company.leetcode.reverse;

class Solution {
    public int reverse(int x) {
        boolean isPositive = true;
        if (x < 0) {
            isPositive = false;
        }

        long abs = Math.abs((long) x);
        String numS = String.valueOf(abs);

        StringBuilder sb = new StringBuilder();
        for (int i = numS.length() - 1; i >= 0; i--) {
            sb.append(numS.charAt(i));
        }

        long reverseAbs = Long.parseLong(sb.toString());
        if (reverseAbs > Integer.MAX_VALUE) {
            reverseAbs = 0;
        }

        if (isPositive) {
            return (int) reverseAbs;
        } else {
            return -1 *  (int) reverseAbs;
        }
    }
}