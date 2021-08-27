package com.company.programmers.kakao2018.뉴스클러스터링;

import java.util.HashMap;
import java.util.Map;

public class Solution3 {
    public int solution(String str1, String str2) {
        int answer = 0;

        Map<String, Integer> wordMap1 = createWordMap(str1);
        Map<String, Integer> wordMap2 = createWordMap(str2);

        if (wordMap1.size() == 0 && wordMap2.size() == 0) {
            answer = 65536;
        } else {
            int interSetCount = countInterSet(wordMap1, wordMap2);
            int unionSetCount = countUnionSet(wordMap1, wordMap2);

            answer = interSetCount * 65536 / unionSetCount;
        }

        return answer;
    }

    private int countUnionSet(Map<String, Integer> wordMap1, Map<String, Integer> wordMap2) {
        int count = 0;
        for (String word : wordMap1.keySet()) {
            if (wordMap2.containsKey(word)) {
                count += wordMap1.get(word) > wordMap2.get(word) ? wordMap1.get(word) : wordMap2.get(word);
            } else {
                count += wordMap1.get(word);
            }
        }

        for (String word : wordMap2.keySet()) {
            if (!wordMap1.containsKey(word)) {
                count += wordMap2.get(word);
            }
        }

        return count;
    }

    private int countInterSet(Map<String, Integer> wordMap1, Map<String, Integer> wordMap2) {
        int count = 0;
        for (String word : wordMap1.keySet()) {
            if (wordMap2.containsKey(word)) {
                count += wordMap1.get(word) < wordMap2.get(word) ? wordMap1.get(word) : wordMap2.get(word);
            }
        }

        return count;
    }

    private Map<String, Integer> createWordMap(String str) {
        Map<String, Integer> wordMap = new HashMap<>();

        for (int i = 0; i < str.length() - 1; i++) {
            String word = str.substring(i, i + 2).toLowerCase();
            if (!Character.isAlphabetic(word.charAt(0)) || !Character.isAlphabetic(word.charAt(1))) {
                continue;
            }
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        }

        return wordMap;
    }
}