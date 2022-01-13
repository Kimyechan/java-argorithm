package com.company.programmers.kakao2021.광고삽입;

public class Solution7 {
    public String solution(String play_time, String adv_time, String[] logs) {
        int playTimeSec = toSec(play_time);
        int advTimeSec = toSec(adv_time);

        long[] times = new long[playTimeSec + 2];
        for (String log : logs) {
            String[] se = log.split("-");
            int startSec = toSec(se[0]);
            int endSec = toSec(se[1]);

            times[startSec] += 1;
            times[endSec] -= 1;
        }

        for (int i = 0; i < playTimeSec; i++) {
            times[i + 1] += times[i];
        }

        for (int i = 0; i < playTimeSec; i++) {
            times[i + 1] += times[i];
        }

        long maxPlayTime = times[advTimeSec - 1];
        int minPlayStartTime = 0;
        for (int startSec = 1; startSec < playTimeSec - advTimeSec + 1; startSec++) {
            long intervalTime = times[startSec + advTimeSec - 1] - times[startSec - 1];
            if (maxPlayTime < intervalTime) {
                maxPlayTime = intervalTime;
                minPlayStartTime = startSec;
            }
        }

        return toHMS(minPlayStartTime);
    }

    private String toHMS(int sec) {
        int hour = sec / 3600;
        sec -= hour * 3600;
        int minute = sec / 60;
        sec -= minute * 60;

        return String.format("%02d:%02d:%02d", hour, minute, sec);
    }

    private int toSec(String time) {
        String[] hms = time.split(":");

        int hour = Integer.parseInt(hms[0]) * 3600;
        int minute = Integer.parseInt(hms[1]) * 60;
        int sec = Integer.parseInt(hms[2]);

        return hour + minute + sec;
    }

    public static void main(String[] args) {
        Solution7 solution = new Solution7();
        System.out.println(solution.solution("02:03:55", "00:14:15",
                new String[]{
                        "01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"
                }
        ));
        System.out.println(solution.solution("99:59:59", "25:00:00",
                new String[]{
                        "69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00"
                }
        ));
        System.out.println(solution.solution("50:00:00", "50:00:00",
                new String[]{
                        "15:36:51-38:21:49", "10:14:18-15:36:51", "38:21:49-42:51:45"
                }
        ));
    }
}
