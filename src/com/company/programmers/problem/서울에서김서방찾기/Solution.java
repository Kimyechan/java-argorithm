package com.company.programmers.problem.서울에서김서방찾기;

class Solution {
    public String solution(String[] seoul) {
        String answer = "";
        int position = 0;

        for (int i = 0; i < seoul.length; i++) {
            if (seoul[i].equals("Kim")) {
                position = i;
                break;
            }
        }

        answer = "김서방은 " + position + "에 있다";
        return answer;
    }
}
