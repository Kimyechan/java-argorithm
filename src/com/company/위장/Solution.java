package com.company.위장;

import java.util.HashMap;

public class Solution {
    public static int solution(String[][] clothes) {
        HashMap<String, String> clothesHash = new HashMap<>();
        HashMap<String, Integer> kindHash = new HashMap<>();

        for (String[] clothe : clothes) {
            clothesHash.put(clothe[0], clothe[1]);
            if (kindHash.containsKey(clothe[1])) {
                kindHash.put(clothe[1], kindHash.get(clothe[1]) + 1);
            } else {
                kindHash.put(clothe[1], 1);
            }
        }

        int answer = 1;
        for(int value : kindHash.values()) {
            answer *= (value + 1);
        }

        return answer-1;
    }

    public static void main(String[] args){
        System.out.println(solution(new String[][]{{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}}));
    }

}
