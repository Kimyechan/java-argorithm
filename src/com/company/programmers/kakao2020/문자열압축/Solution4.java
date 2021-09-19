package com.company.programmers.kakao2020.문자열압축;

public class Solution4 {
    public int solution(String s) {
        int answer = Integer.MAX_VALUE;

        for (int i = 1; i < s.length() / 2 + 1; i++) {
            String newS = compress(i, s);

            answer = Math.min(answer, newS.length());
        }

        if (s.length() == 1) {
            return 1;
        } else {
            return answer;
        }
    }

    public String compress(int len, String s) {
        StringBuilder sb = new StringBuilder();

        String prev = "";
        String now = "";
        int count = 0;
        for (int i = 0; i < s.length() / len + 1; i++) {
            int from = len * i;
            int to = len * (i + 1);

            if (to > s.length()) {
                to = s.length();
            }

            now = s.substring(from, to);
            if (prev.equals(now)) {
                count += 1;
            } else {
                if (count == 0) {
                    sb.append(prev);
                } else {
                    sb.append(count + 1).append(prev);
                    count = 0;
                }
                prev = now;
            }
        }

        if (count == 0) {
            sb.append(prev);
        } else {
            sb.append(count + 1).append(prev);
        }

        return sb.toString();
    }
}