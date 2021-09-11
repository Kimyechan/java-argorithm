package com.company.programmers.kakao2022.s3;

import java.util.*;

public class Solution {
    Map<Integer, Park> parkMap = new HashMap<>();

    static class Park {
        int number;
        int inTime;
        int totalTime;
        boolean isParking;
    }

    static class Fee {
        int number;
        int cost;

        public Fee(int number, int cost) {
            this.number = number;
            this.cost = cost;
        }
    }

    public int[] solution(int[] fees, String[] records) {
        int[] answer = {};

        for (String record : records) {
            String[] rSplit = record.split(" ");
            int time = changeTimeToMin(rSplit[0]);
            int number = Integer.parseInt(rSplit[1]);
            String dir = rSplit[2];

            if (dir.equals("IN")) {
                if (parkMap.containsKey(number)) {
                    Park park = parkMap.get(number);
                    park.inTime = time;
                    park.isParking = true;
                } else {
                    Park park = new Park();
                    park.number = number;
                    park.inTime = time;
                    park.isParking = true;
                    parkMap.put(number, park);
                }
            } else if (dir.equals("OUT")) {
                if (parkMap.containsKey(number)) {
                    Park park = parkMap.get(number);
                    park.totalTime += time - park.inTime;
                    park.inTime = 0;
                    park.isParking = false;
                }
            }
        }

        List<Fee> feeList = new ArrayList<>();
        for (Park park : parkMap.values()) {
            int lastTotalTime = park.totalTime;
            if (park.isParking) {
                lastTotalTime += (1439 - park.inTime);
            }
            int totalFee = fees[1];
            if (lastTotalTime > fees[0]) {
                int remainTime = lastTotalTime - fees[0];
                totalFee += (int) (Math.ceil((double) remainTime / fees[2])) * fees[3];
            }
            feeList.add(new Fee(park.number, totalFee));
        }

        feeList.sort(Comparator.comparingInt(f -> f.number));
        answer = new int[feeList.size()];
        for (int i = 0; i < feeList.size(); i++) {
            answer[i] = feeList.get(i).cost;
        }

        return answer;
    }

    private int changeTimeToMin(String time) {
        String[] hm = time.split(":");

        return Integer.parseInt(hm[0]) * 60 + Integer.parseInt(hm[1]);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println(Arrays.toString(solution.solution(
//                new int[]{180, 5000, 10, 600},
//                new String[]{"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"}
//        )));
        System.out.println(Arrays.toString(solution.solution(
                new int[]{1, 461, 1, 10},
                new String[]{"00:00 1234 IN"}
        )));
    }
}