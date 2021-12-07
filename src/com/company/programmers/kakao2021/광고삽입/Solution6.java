package com.company.programmers.kakao2021.광고삽입;

public class Solution6 {
    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";

        int playTimeSec = changeTimeToSec(play_time);
        int advTimeSec = changeTimeToSec(adv_time);

        long[] times = new long[playTimeSec + 1];
        for (String log : logs) {
            String[] interval = log.split("-");
            int startSec = changeTimeToSec(interval[0]);
            int endSec = changeTimeToSec(interval[1]);

            times[startSec] += 1;
            times[endSec] -= 1;
        }

        for (int i = 0; i < playTimeSec; i++) {
            times[i + 1] += times[i];
        }

        for (int i = 0; i < playTimeSec; i++) {
            times[i + 1] += times[i];
        }

        long maxStartTimeSec = 0;
        long maxPlayTimeSec = times[advTimeSec - 1];
        for (int i = 1; i < playTimeSec - advTimeSec + 1; i++) {
            long playTime = times[i + advTimeSec - 1] - times[i - 1];
            if (maxPlayTimeSec < playTime) {
                maxPlayTimeSec = playTime;
                maxStartTimeSec = i;
            }
        }

        answer = changeSecToHMS(maxStartTimeSec);

        return answer;
    }

    private String changeSecToHMS(long maxPlayTimeSec) {
        long hour = maxPlayTimeSec / 3600;
        maxPlayTimeSec = maxPlayTimeSec % 3600;

        long minute = maxPlayTimeSec / 60;
        maxPlayTimeSec = maxPlayTimeSec % 60;

        long sec = maxPlayTimeSec;

        return String.format("%02d:%02d:%02d", hour, minute, sec);
    }

    private int changeTimeToSec(String play_time) {
        String[] time = play_time.split(":");

        int hour = Integer.parseInt(time[0]);
        int minute = Integer.parseInt(time[1]);
        int sec = Integer.parseInt(time[2]);

        return 3600 * hour + 60 * minute + sec;
    }

    public static void main(String[] args) {
        Solution6 solution = new Solution6();
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
