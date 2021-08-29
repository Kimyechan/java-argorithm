package com.company.programmers.kakao2019.무지의먹방라이브;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solution3 {

    static class Food {
        int order;
        long time;

        public Food(int order, long time) {
            this.order = order;
            this.time = time;
        }
    }

    public int solution(int[] food_times, long k) {
        int answer = 0;

        List<Food> foodList = new ArrayList<>();
        for (int i = 0; i < food_times.length; i++) {
            foodList.add(new Food(i + 1, food_times[i]));
        }

        foodList.sort(Comparator.comparingLong(f -> f.time));

        long time = 0;
        long prevTime = 0;
        int lastIdx = 0;
        for (int i = 0; i < foodList.size(); i++) {
            if (i == 0) {
                time += foodList.get(i).time * foodList.size();
            } else {
                prevTime = time;
                time += (foodList.get(i).time - foodList.get(i - 1).time) * (foodList.size() - i);
                lastIdx = i;
            }
            if (time > k) {
                break;
            }
        }

        if (time <= k) {
            answer = -1;
        } else {
            long remainTime = k - prevTime;
            List<Food> remainFoodList = foodList.subList(lastIdx, foodList.size());
            remainFoodList.sort(Comparator.comparingInt(f -> f.order));

            answer = remainFoodList.get((int) (remainTime % remainFoodList.size())).order;
        }

        return answer;
    }
}