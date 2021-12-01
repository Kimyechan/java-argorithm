package com.company.programmers.problem.가운데글자가져오기;

class Solution {
    public String solution(String s) {
        String answer = "";

        int len = s.length();
        int mid = len / 2;
        if (len % 2 == 0) {
            answer = s.substring(mid, mid + 2);
        } else {
            answer = s.substring(mid, mid + 1);
        }

        return answer;
    }
}
