package com.company.programmers.kakao2021.광고삽입;

public class Solution3 {
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

        for (int i = 0; i < playTimeSec; i++) {
            watchingTimes[i + 1] += watchingTimes[i];
        }

        long maxTime = watchingTimes[advTimeSec - 1];
        int maxStartTime = 0;
        for (int i = 0; i < playTimeSec - advTimeSec; i++) {
            long temp = watchingTimes[i + advTimeSec] - watchingTimes[i];

            if (temp > maxTime) {
                maxTime = temp;
                maxStartTime = i + 1;
            }
        }

        answer = changeTimeToString(maxStartTime);

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

    private int changeTimeToSec(String time) {
        String[] hMS = time.split(":");

        int hour = Integer.parseInt(hMS[0]) * 3600;
        int minute = Integer.parseInt(hMS[1]) * 60;
        int sec = Integer.parseInt(hMS[2]);

        return hour + minute + sec;
    }

    public static void main(String[] args) {
        Solution3 solution3 = new Solution3();
        System.out.println(solution3.solution("02:03:55", "00:14:15",
                new String[]{
                        "01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"
                }
        ));
        System.out.println(solution3.solution("99:59:59", "25:00:00",
                new String[]{
                        "69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00"
                }
        ));
        System.out.println(solution3.solution("50:00:00", "50:00:00",
                new String[]{
                        "15:36:51-38:21:49", "10:14:18-15:36:51", "38:21:49-42:51:45"
                }
        ));
    }
}