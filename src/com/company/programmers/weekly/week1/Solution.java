package com.company.programmers.weekly.week1;

class Solution {
    public long solution(int price, int money, int count) {
        long answer = 0;

        long use = 0;
        for (int i = 1; i <= count; i++) {
            use += price * i;
        }

        if (money < use) {
            answer = use - money;
        } else {
            answer = 0;
        }

        return answer;
    }
}
