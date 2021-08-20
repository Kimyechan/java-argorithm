package com.company.programmers.kakao2018.셔틀버스;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution2 {
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";

        List<Integer> minutes = new ArrayList<>();
        for (String time : timetable) {
            int minute = changeToMinute(time);
            minutes.add(minute);
        }
        Collections.sort(minutes);

        for (int i = 0; i < n - 1; i++) {
            int arriveTime = 9 * 60 + i * t;
            int arriveCount = 0;
            while (minutes.size() != 0 && arriveTime >= minutes.get(0)) {
                if (arriveCount == m) {
                    break;
                }
                minutes.remove(0);
                arriveCount++;
            }
        }

        int lastArriveTime = 9 * 60 + (n - 1) * t;
        int prevTimeIdx = 0;
        while (minutes.size() > prevTimeIdx && lastArriveTime >= minutes.get(prevTimeIdx)) {
            if (prevTimeIdx == m) {
                break;
            }
            prevTimeIdx++;
        }

        int conArriveTime = 0;
        if (prevTimeIdx < m) {
            conArriveTime = lastArriveTime;
        } else {
            conArriveTime = minutes.get(prevTimeIdx - 1) - 1;
        }

        answer = changeToHourMinute(conArriveTime);
        return answer;
    }

    private String changeToHourMinute(int conArriveTime) {
        int hour = conArriveTime / 60;
        int minute = conArriveTime % 60;

        return String.format("%02d:%02d", hour, minute);
    }

    private int changeToMinute(String time) {
        String[] hourMinute = time.split(":");
        return 60 * Integer.parseInt(hourMinute[0]) + Integer.parseInt(hourMinute[1]);
    }
}