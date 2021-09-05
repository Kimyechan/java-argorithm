package com.company.programmers.kakao2021.광고삽입;

public class Solution4 {
    long[] watchingTimes;

    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";

        int playTimeSec = changeToSecTime(play_time);
        int advTimeSec = changeToSecTime(adv_time);

        watchingTimes = new long[playTimeSec + 1];
        for (String log : logs) {
            String[] se = log.split("-");
            int startSec = changeToSecTime(se[0]);
            int endSec = changeToSecTime(se[1]);
            watchingTimes[startSec] += 1;
            watchingTimes[endSec] -= 1;
        }

        for (int i = 0; i < playTimeSec; i++) {
            watchingTimes[i + 1] += watchingTimes[i];
        }

        int maxStartSec = findMaxStartSec(watchingTimes, advTimeSec, playTimeSec);

        answer = changeSecToTimeString(maxStartSec);

        return answer;
    }

    private String changeSecToTimeString(int maxStartSec) {
        int hour = maxStartSec / 3600;
        maxStartSec %= 3600;
        int minute = maxStartSec / 60;
        maxStartSec %= 60;
        int sec = maxStartSec;

        return String.format("%02d:%02d:%02d", hour, minute, sec);
    }

    private int findMaxStartSec(long[] watchingTimes, int advTimeSec, int playTimeSec) {
        int maxStartSec = 0;
        long watchingTime = 0;
        for (int i = 0; i < advTimeSec; i++) {
            watchingTime += watchingTimes[i];
        }
        long maxWatchingTime = watchingTime;

        for (int i = 0; i < playTimeSec - advTimeSec; i++) {
            watchingTime -= watchingTimes[i];
            watchingTime += watchingTimes[i + advTimeSec];

            if (maxWatchingTime < watchingTime) {
                maxWatchingTime = watchingTime;
                maxStartSec = i + 1;
            }
        }

        return maxStartSec;
    }

    private int changeToSecTime(String time) {
        String[] hMS = time.split(":");
        int hour = Integer.parseInt(hMS[0]) * 3600;
        int minute = Integer.parseInt(hMS[1]) * 60;
        int sec = Integer.parseInt(hMS[2]);

        return hour + minute + sec;
    }

    public static void main(String[] args) {
        Solution4 solution4 = new Solution4();
        System.out.println(solution4.solution("02:03:55", "00:14:15",
                new String[]{
                        "01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"
                }
        ));
//        System.out.println(solution4.solution("99:59:59", "25:00:00",
//                new String[]{
//                        "69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00"
//                }
//        ));
//        System.out.println(solution4.solution("50:00:00", "50:00:00",
//                new String[]{
//                        "15:36:51-38:21:49", "10:14:18-15:36:51", "38:21:49-42:51:45"
//                }
//        ));
    }
}