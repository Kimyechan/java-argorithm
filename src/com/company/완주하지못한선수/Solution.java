package com.company.완주하지못한선수;

import java.util.HashMap;

public class Solution {
    public static String solution(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String, Integer> completePerson = new HashMap();


        for(int i = 0; i < participant.length; i++){
            if(completePerson.containsKey(participant[i])) {
                completePerson.put(participant[i], completePerson.get(participant[i]) + 1);
            } else {
                completePerson.put(participant[i], 1);
            }
        }

        for(int i = 0; i < completion.length; i++){
            if(completePerson.containsKey(completion[i])) {
                completePerson.put(completion[i], completePerson.get(completion[i])-1);
            }
        }

        for(int i = 0; i < participant.length; i++) {
            if(completePerson.get(participant[i]) > 0){
                answer = participant[i];
            }
        }

        return answer;
    }

    public static void main(String[] args){
        String answer = solution(new String[]{"leo", "kiki", "eden", "leo"}, new String[]{"eden", "kiki", "leo"});
        System.out.println(answer);
    }
}
