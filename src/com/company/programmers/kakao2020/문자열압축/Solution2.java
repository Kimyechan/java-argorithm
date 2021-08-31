package com.company.programmers.kakao2020.문자열압축;

public class Solution2 {
    public int solution(String s) {
        int sLen = s.length();
        int minLen = sLen;
        for (int i = 1; i < sLen / 2 + 1; i++) {
            minLen = Math.min(minLen, getCompressLen(s, i));
        }

        return minLen;
    }

    private int getCompressLen(String s, int len) {
        StringBuilder sb = new StringBuilder();
        int div = s.length() / len;

        int count = 1;
        String now = s.substring(0, len);
        String temp = now;
        for (int i = 1; i < div; i++) {
            String next = s.substring(i * len, (i + 1) * len);

            if (now.equals(next)) {
                count += 1;
            } else {
                if (count == 1) {
                    sb.append(now);
                } else {
                    sb.append(count).append(now);
                }
                count = 1;
            }
            temp = now;
            now = next;
        }

        if (temp.equals(now)) {
            sb.append(count).append(now);
        } else {
            sb.append(now);
        }

        int mod = s.length() % len;
        if (mod != 0) {
            sb.append(s.substring(s.length() - mod));
        }

        return sb.toString().length();
    }

    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
        System.out.println(solution2.solution("aabbaccc"));
    }
}