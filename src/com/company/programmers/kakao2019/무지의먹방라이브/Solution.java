package com.company.programmers.kakao2019.무지의먹방라이브;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solution {

    static class Food {
        int order;
        long amount;

        public Food(int order, long amount) {
            this.order = order;
            this.amount = amount;
        }
    }

    public int solution(int[] food_times, long k) {
        int answer = 0;
        int foodTimeLen = food_times.length;

        List<Food> foods = new ArrayList<>();
        for (int i = 0; i < foodTimeLen; i++) {
            foods.add(new Food(i, food_times[i]));
        }
        foods.sort(Comparator.comparingLong(f -> f.amount));

        long time = 0;
        long prevTime = 0;
        long remainTime = 0;
        int lastIdx = 0;
        for (int i = 0; i < foodTimeLen; i++) {
            if (i == 0) {
                time += foods.get(0).amount * foods.size();
            } else {
                prevTime = time;
                time += (foods.get(i).amount - foods.get(i - 1).amount) * (foods.size() - i);
            }
            if (time > k) {
                remainTime = k - prevTime;
                lastIdx = i;
                break;
            }
        }

        if (time <= k) {
            return -1;
        }

        List<Food> remainFoods = foods.subList(lastIdx, foods.size());
        remainFoods.sort(Comparator.comparingInt(f -> f.order));

        answer = remainFoods.get((int) (remainTime % remainFoods.size())).order + 1;

        return answer;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println(solution.solution(new int[]{1, 1, 1, 1}, 4));
        System.out.println(solution.solution(new int[]{3, 1, 2}, 5));
    }
}