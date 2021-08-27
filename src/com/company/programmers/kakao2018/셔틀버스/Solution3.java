package com.company.programmers.kakao2018.셔틀버스;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Solution3 {
    List<Integer> arriveTimeList = new LinkedList<>();

    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        for (String time : timetable) {
            String[] hM = time.split(":");
            int hour = Integer.parseInt(hM[0]) * 60;
            int minute = Integer.parseInt(hM[1]);
            arriveTimeList.add(hour + minute);
        }
        Collections.sort(arriveTimeList);

        int startTime = 9 * 60;
        for (int i = 0; i < n - 1; i++) {
            int busTime = 9 * 60 + t * i;
            int arriveCount = 0;
            while (arriveTimeList.size() != 0 && busTime >= arriveTimeList.get(0)) {
                if (arriveCount >= m) {
                    break;
                }
                arriveTimeList.remove(0);
                arriveCount++;
            }
        }

        int lastTime;
        int lastBusTime = startTime + (n - 1) * t;
        if (arriveTimeList.size() < m) {
            lastTime = lastBusTime;
        } else {
            if (lastBusTime < arriveTimeList.get(m - 1)) {
                lastTime = lastBusTime;
            } else {
                lastTime = arriveTimeList.get(m - 1) - 1;
            }
        }

        answer = changeToHourMinute(lastTime);

        return answer;
    }

    private String changeToHourMinute(int lastTime) {
        int hour = lastTime / 60;
        int minute = lastTime % 60;

        return String.format("%02d:%02d", hour, minute);
    }

    public static void main(String[] args) {
        Solution3 solution3 = new Solution3();
//        System.out.println(solution3.solution(1, 1, 5, new String[]{"08:00", "08:01", "08:02", "08:03"}));
        System.out.println(solution3.solution(10, 60, 45, new String[]{"23:59","23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59"}));
    }
}