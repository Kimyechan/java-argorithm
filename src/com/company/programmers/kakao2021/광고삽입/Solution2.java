package com.company.programmers.kakao2021.광고삽입;

import java.util.ArrayList;
import java.util.List;

public class Solution2 {
    static long[] watchingTimes;

    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";

        int playTimeSec = changeTimeToSec(play_time);
        int advTimeSec = changeTimeToSec(adv_time);

        watchingTimes = new long[playTimeSec + 1];

        for (String log : logs) {
            String[] se = log.split("-");
            int startTime = changeTimeToSec(se[0]);
            int endTime = changeTimeToSec(se[1]);
            watchingTimes[startTime] += 1;
            watchingTimes[endTime] -= 1;
        }

        for (int i = 0; i < playTimeSec; i++) {
            watchingTimes[i + 1] += watchingTimes[i];
        }

        List<Integer> candidates = new ArrayList<>();
        long maxTime = findMaxWatchTime(playTimeSec, advTimeSec);

        long watchTime = 0;
        for (int i = 0; i < advTimeSec; i++) {
            watchTime += watchingTimes[i];
        }
        if (watchTime == maxTime) {
            candidates.add(0);
        }

        for (int i = 0; i < playTimeSec - advTimeSec + 1; i++) {
            watchTime -= watchingTimes[i];
            watchTime += watchingTimes[i + advTimeSec];
            if (watchTime == maxTime) {
                candidates.add(i + 1);
            }
        }

        answer = changeTimeToString(candidates.get(0));

        return answer;
    }

    private String changeTimeToString(Integer time) {
        int hour = time / 3600;
        time %= 3600;
        int minute = time / 60;
        time %= 60;
        int sec = time;

        return String.format("%02d:%02d:%02d", hour, minute, sec);
    }

    private long findMaxWatchTime(int playTimeSec, int advTimeSec) {
        long watchTime = 0;
        for (int i = 0; i < advTimeSec; i++) {
            watchTime += watchingTimes[i];
        }
        long maxTime = watchTime;

        for (int i = 0; i < playTimeSec - advTimeSec; i++) {
            watchTime -= watchingTimes[i];
            watchTime += watchingTimes[i + advTimeSec];
            if (maxTime < watchTime) {
                maxTime = watchTime;
            }
        }

        return maxTime;
    }

    private int changeTimeToSec(String time) {
        String[] hMS = time.split(":");

        int hour = Integer.parseInt(hMS[0]) * 3600;
        int minute = Integer.parseInt(hMS[1]) * 60;
        int sec = Integer.parseInt(hMS[2]);

        return hour + minute + sec;
    }

    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
        System.out.println(solution2.solution("02:03:55", "00:14:15",
                new String[]{
                        "01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"
                }
        ));
        System.out.println(solution2.solution("99:59:59", "25:00:00",
                new String[]{
                        "69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00"
                }
        ));
        System.out.println(solution2.solution("50:00:00", "50:00:00",
                new String[]{
                        "15:36:51-38:21:49", "10:14:18-15:36:51", "38:21:49-42:51:45"
                }
        ));
    }
}