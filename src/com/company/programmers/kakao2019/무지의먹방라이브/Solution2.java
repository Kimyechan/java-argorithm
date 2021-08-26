package com.company.programmers.kakao2019.무지의먹방라이브;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solution2 {

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
        int foodLen = food_times.length;

        for (int i = 0; i < foodLen; i++) {
            foodList.add(new Food(i + 1, food_times[i]));
        }
        foodList.sort(Comparator.comparingLong(f -> f.time));

        long time = 0;
        long prevTime = 0;
        int lastIdx = 0;
        for (int i = 0; i < foodLen; i++) {
            if (i == 0) {
                time += foodList.get(0).time * foodLen;
            } else {
                prevTime = time;
                time += (foodList.get(i).time - foodList.get(i - 1).time) * (foodLen - i);
            }

            if (time > k) {
                lastIdx = i;
                break;
            }
        }

        if (time <= k) {
            return -1;
        }

        List<Food> remainFoodList = foodList.subList(lastIdx, foodLen);
        remainFoodList.sort(Comparator.comparingInt(f -> f.order));

        long remainTime = k - prevTime;
        int idxRestore = (int) (remainTime % remainFoodList.size());

        answer = remainFoodList.get(idxRestore).order;

        return answer;
    }
}