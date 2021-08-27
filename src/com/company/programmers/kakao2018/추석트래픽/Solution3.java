package com.company.programmers.kakao2018.추석트래픽;

import java.util.ArrayList;
import java.util.List;

public class Solution3 {
    static List<Log> logList = new ArrayList<>();

    static class Log {
        int startTimeSec;
        int endTimeSec;

        public Log(int startTimeSec, int endTimeSec) {
            this.startTimeSec = startTimeSec;
            this.endTimeSec = endTimeSec;
        }
    }

    public int solution(String[] lines) {
        int answer = 0;
        for (String line : lines) {
            String[] log = line.split(" ");
            String[] hMS = log[1].split(":");
            int endTimeSec = changeToSec(hMS);
            int interval = (int) (Double.parseDouble(log[2].substring(0, log[2].length() - 1)) * 1000);
            int startTimeSec = endTimeSec - interval + 1;

            logList.add(new Log(startTimeSec, endTimeSec));
        }

        int maxThroughput = 0;
        for (int i = 0; i < logList.size(); i++) {
            int throughput = 0;
            int startIntervalSec = logList.get(i).startTimeSec;
            int endIntervalSec = startIntervalSec + 1000 - 1;
            for (Log log : logList) {
                if (log.endTimeSec < startIntervalSec || log.startTimeSec > endIntervalSec) {
                    continue;
                }
                throughput += 1;
            }
            maxThroughput = Math.max(maxThroughput, throughput);
        }

        for (int i = 0; i < logList.size(); i++) {
            int throughput = 0;
            int startIntervalSec = logList.get(i).endTimeSec;
            int endIntervalSec = startIntervalSec + 1000 - 1;
            for (Log log : logList) {
                if (log.endTimeSec < startIntervalSec || log.startTimeSec > endIntervalSec) {
                    continue;
                }
                throughput += 1;
            }
            maxThroughput = Math.max(maxThroughput, throughput);
        }

        answer = maxThroughput;

        return answer;
    }

    private int changeToSec(String[] hMS) {
        int hour = Integer.parseInt(hMS[0]) * 3600 * 1000;
        int minute = Integer.parseInt(hMS[1]) * 60 * 1000;
        int sec = (int) (Double.parseDouble(hMS[2]) * 1000);

        return hour + minute + sec;
    }
}