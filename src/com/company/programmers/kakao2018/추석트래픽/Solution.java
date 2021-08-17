package com.company.programmers.kakao2018.추석트래픽;

public class Solution {
    static long[][] arranges;

    public int solution(String[] lines) {
        int answer = 0;

        arranges = new long[lines.length][2];
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            String[] dateTime = line.split(" ");
            String[] time = dateTime[1].split(":");

            long endTime = calcStartTimeSec(time);
            long passTime = calcPassTimeSec(dateTime[2]);
            long startTime = endTime - passTime + 1;

            arranges[i][0] = startTime;
            arranges[i][1] = endTime;
            System.out.printf("%d %d\n", startTime, endTime);
        }

        int maxCount = Integer.MIN_VALUE;
        for (int i = 0; i < lines.length; i++) {
            int count = 0;
            for (int j = 0; j < lines.length; j++) {
                long start = arranges[i][0];
                long end = arranges[i][0] + 1000 - 1;
                if (start > arranges[j][1] || end < arranges[j][0]) {
                    continue;
                }

                count++;
                maxCount = Math.max(maxCount, count);
            }

            count = 0;
            for (int j = 0; j < lines.length; j++) {
                long start = arranges[i][1];
                long end = arranges[i][1] + 1000 - 1;
                if (start > arranges[j][1] || end < arranges[j][0]) {
                    continue;
                }

                count++;
                maxCount = Math.max(maxCount, count);
            }
        }

        answer = maxCount;
        return answer;
    }

    private long calcPassTimeSec(String s) {
        return (long) (Double.parseDouble(s.substring(0, s.length() - 1)) * 1000);
    }

    private long calcStartTimeSec(String[] time) {
        long startTimeHour = Long.parseLong(time[0]) * 3600 * 1000;
        long startTimeMin = Long.parseLong(time[1]) * 60 * 1000;
        long startTimeSec = (long) (Double.parseDouble(time[2]) * 1000);

        return startTimeHour + startTimeMin + startTimeSec;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(new String[]{
                "2016-09-15 20:59:57.421 0.351s",
                "2016-09-15 20:59:58.233 1.181s",
                "2016-09-15 20:59:58.299 0.8s",
                "2016-09-15 20:59:58.688 1.041s",
                "2016-09-15 20:59:59.591 1.412s",
                "2016-09-15 21:00:00.464 1.466s",
                "2016-09-15 21:00:00.741 1.581s",
                "2016-09-15 21:00:00.748 2.31s",
                "2016-09-15 21:00:00.966 0.381s",
                "2016-09-15 21:00:02.066 2.62s"}));
    }
}