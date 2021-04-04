package com.company.programmers.kakao2021.광고삽입;

class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        int playTimeSec = changeTimeToSec(play_time);
        int advTimeSec = changeTimeToSec(adv_time);

        long[] totalSecs = new long[playTimeSec + 1];

        int[] logStartSecs = new int[logs.length];
        int[] logEndSecs = new int[logs.length];

        for (int i = 0; i < logs.length; i++) {
            String[] logsSplit = logs[i].split("-");
            logStartSecs[i] = changeTimeToSec(logsSplit[0]);
            logEndSecs[i] = changeTimeToSec(logsSplit[1]);
        }

        for (int i = 0; i < logs.length; i++) {
            totalSecs[logStartSecs[i]] += 1;
            totalSecs[logEndSecs[i]] -= 1;
        }

        for (int i = 1; i < totalSecs.length; i++) {
            totalSecs[i] = totalSecs[i] + totalSecs[i - 1];
        }

        for (int i = 1; i < totalSecs.length; i++) {
            totalSecs[i] = totalSecs[i] + totalSecs[i - 1];
        }

        long maxSec = 0;
        long maxStartSec = 0;
        for (int i = advTimeSec - 1; i < playTimeSec; i++) {
            if (i == advTimeSec - 1) {
                maxSec = totalSecs[i];
                maxStartSec = 0;
            } else {
                if (maxSec < totalSecs[i] - totalSecs[i - advTimeSec]) {
                    maxSec = totalSecs[i] - totalSecs[i - advTimeSec];
                    maxStartSec = i - advTimeSec + 1;
                }
            }
        }

        return changeSecToTime(maxStartSec);
    }

    private String changeSecToTime(long maxStartSec) {
        long hour = Math.floorDiv(maxStartSec, 3600);
        maxStartSec = Math.floorMod(maxStartSec, 3600);

        long minute = Math.floorDiv(maxStartSec, 60);;
        maxStartSec = Math.floorMod(maxStartSec, 60);

        long sec = maxStartSec;

        return String.format("%02d:%02d:%02d", hour, minute, sec);
    }

    private int changeTimeToSec(String time) {
        String[] timeSplit = time.split(":");
        return Integer.parseInt(timeSplit[0]) * 3600 + Integer.parseInt(timeSplit[1]) * 60 + Integer.parseInt(timeSplit[2]);
    }
}
