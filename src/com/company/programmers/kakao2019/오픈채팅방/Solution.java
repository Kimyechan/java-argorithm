package com.company.programmers.kakao2019.오픈채팅방;

import java.util.*;

public class Solution {
    Map<String, String> nameMap = new HashMap<>();

    public String[] solution(String[] record) {
        String[] answer = {};
        for (String r : record) {
            String[] commands = r.split(" ");
            if (commands[0].equals("Enter") || commands[0].equals("Change")) {
                nameMap.put(commands[1], commands[2]);
            }
        }

        List<String> outputList = new ArrayList<>();
        for (String r : record) {
            String[] commands = r.split(" ");
            if (commands[0].equals("Enter")) {
                outputList.add(String.format("%s님이 들어왔습니다.", nameMap.get(commands[1])));
            } else if (commands[0].equals("Leave")) {
                outputList.add(String.format("%s님이 나갔습니다.", nameMap.get(commands[1])));
            }
        }

        answer = new String[outputList.size()];
        for (int i = 0; i < outputList.size(); i++) {
            answer[i] = outputList.get(i);
        }

        return answer;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.solution(new String[]{"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan"})));
    }
}