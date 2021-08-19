package com.company.programmers.kakao2018.추석트래픽;

public class Solution2 {
    static int[][] arranges;

    public int solution(String[] lines) {
        int answer = 0;

        arranges = new int[lines.length][2];
        for (int i = 0; i < lines.length; i++) {
            String[] dateTime = lines[i].split(" ");
            String[] endTime = dateTime[1].split(":");
            int endTimeSec = changeTimeToSec(endTime);
            int diff = extractDiffSec(dateTime[2]);
            int startTimeSec = endTimeSec - diff + 1;

            arranges[i] = new int[]{startTimeSec, endTimeSec};
        }

        int maxThroughput = Integer.MIN_VALUE;
        for (int i = 0; i < lines.length; i++) {
            int startThroughput = countThroughput(arranges, arranges[i][0]);
            maxThroughput = Math.max(maxThroughput, startThroughput);

            int endThroughput = countThroughput(arranges, arranges[i][1]);
            maxThroughput = Math.max(maxThroughput, endThroughput);
        }

        answer = maxThroughput;

        return answer;
    }

    private int countThroughput(int[][] arranges, int startSec) {
        int throughput = 0;
        int endSec = startSec + 1000 - 1;

        for (int i = 0; i < arranges.length; i++) {
            if (startSec > arranges[i][1] || endSec < arranges[i][0]) {
                continue;
            }

            throughput++;
        }

        return throughput;
    }

    private int extractDiffSec(String s) {
        return (int) (Double.parseDouble(s.substring(0, s.length() - 1)) *  1000);
    }

    private int changeTimeToSec(String[] time) {
        int hourSec = Integer.parseInt(time[0]) * 3600 * 1000;
        int minuteSec = Integer.parseInt(time[1]) * 60 * 1000;
        int sec = (int) (Double.parseDouble(time[2]) * 1000);

        return hourSec + minuteSec + sec;
    }
}