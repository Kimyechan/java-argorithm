package com.company.programmers.kakao2021.광고삽입;

class Solution5 {
    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";
        int playTimeSec = changeToSec(play_time);
        int advTimeSec = changeToSec(adv_time);

        long[] time = new long[playTimeSec + 1];
        for (String log : logs) {
            String[] se = log.split("-");
            int startTimeSec = changeToSec(se[0]);
            int endTimeSec = changeToSec(se[1]);

            time[startTimeSec] += 1;
            time[endTimeSec] -= 1;
        }

        for (int i = 0; i < playTimeSec; i++) {
            time[i + 1] += time[i];
        }

        for (int i = 0; i < playTimeSec; i++) {
            time[i + 1] += time[i];
        }

        int insertTime = 0;
        long maxPlayTime = time[advTimeSec - 1];
        for (int i = 1; i < playTimeSec - advTimeSec + 2; i++) {
            if (maxPlayTime < time[i + advTimeSec - 1] - time[i - 1]) {
                maxPlayTime = time[i + advTimeSec - 1] - time[i - 1];
                insertTime = i;
            }
        }

        answer = changeToHMS(insertTime);

        return answer;
    }

    public String changeToHMS(int time) {
        int hour = time / 3600;
        time %= 3600 ;
        int minute = time / 60;
        time %= 60;
        int sec = time;

        return String.format("%02d:%02d:%02d", hour, minute, sec);
    }

    public int changeToSec(String time) {
        String[] hms = time.split(":");
        int hour = Integer.parseInt(hms[0]);
        int minute = Integer.parseInt(hms[1]);
        int sec = Integer.parseInt(hms[2]);

        return hour * 3600 + minute * 60 + sec;
    }
}
