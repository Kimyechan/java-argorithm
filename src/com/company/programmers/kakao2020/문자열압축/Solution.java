package com.company.programmers.kakao2020.문자열압축;

public class Solution {
    public int solution(String s) {
        int answer = s.length();
        for (int i = 1; i <= s.length() / 2; i++) {
            int compressLen = compressString(s, i);

            answer = Math.min(answer, compressLen);
        }

        return answer;
    }

    private int compressString(String s, int len) {
        StringBuilder sb = new StringBuilder();

        String prev = "";
        String now = "";

        int count = 0;
        for (int i = 0; i <= s.length() / len; i++) {
            int from = i * len;
            int to = (i + 1) * len;

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
                }
                count = 0;
            }
            prev = now;
        }

        if (count == 0) {
            sb.append(prev);
        } else {
            sb.append(count + 1).append(prev);
        }

        return sb.toString().length();
    }
}