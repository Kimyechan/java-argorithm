package com.company.programmers.kakao2018.셔틀버스;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        List<Integer> arriveMinutes = changeToMinute(timetable);
        Collections.sort(arriveMinutes);

        int startMinute = 9 * 60;
        for (int i = 0; i < n - 1; i++) {
            int nextMinute = startMinute + i * t;
            int maxRide = m;
            while (arriveMinutes.size() != 0 && nextMinute >= arriveMinutes.get(0)) {
                if (maxRide == 0) {
                    break;
                }
                arriveMinutes.remove(0);
                maxRide--;
            }
        }

        int conArriveTimeMin = 0;
        int lastArriveMinute = 9 * 60 + (n - 1) * t;
        if (arriveMinutes.size() < m) {
            conArriveTimeMin = lastArriveMinute;
        } else if (arriveMinutes.size() >= m && arriveMinutes.get(m - 1) > lastArriveMinute) {
            conArriveTimeMin = lastArriveMinute;
        } else if (arriveMinutes.size() >= m && arriveMinutes.get(m - 1) <= lastArriveMinute) {
            conArriveTimeMin = arriveMinutes.get(m - 1) - 1;
        }

        answer = changeToHourMinString(conArriveTimeMin);
        return answer;
    }

    private String changeToHourMinString(int conArriveTimeMin) {
        String hour = String.valueOf(conArriveTimeMin / 60);
        if (hour.length() == 1) {
            hour = "0" + hour;
        }
        String minute = String.valueOf(conArriveTimeMin % 60);
        if (minute.length() == 1) {
            minute = "0" + minute;
        }

        return hour + ":" + minute;
    }

    private List<Integer> changeToMinute(String[] timetable) {
        List<Integer> arriveMinutes = new ArrayList<>();
        for (String time : timetable) {
            String[] hm = time.split(":");
            int minute = Integer.parseInt(hm[0]) * 60 + Integer.parseInt(hm[1]);
            arriveMinutes.add(minute);
        }

        return arriveMinutes;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(2, 1, 2, new String[]{"09:00", "09:00", "09:00", "09:00"}));
    }
}