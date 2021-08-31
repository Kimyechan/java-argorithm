package com.company.programmers.kakao2020.가사검색;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution3 {

    static class Trie {
        Map<Integer, Integer> lenMap = new HashMap<>();
        Trie[] children = new Trie[26];

        public void insert(String word) {
            Trie node = this;
            int wordLen = word.length();
            lenMap.put(wordLen, lenMap.getOrDefault(wordLen, 0) + 1);

            for (int i = 0; i < wordLen; i++) {
                int alphabet = word.charAt(i) - 'a';
                if (node.children[alphabet] == null) {
                    node.children[alphabet] = new Trie();
                }
                node = node.children[alphabet];
                node.lenMap.put(wordLen, node.lenMap.getOrDefault(wordLen, 0) + 1);
            }
        }

        public int find(String str, int i) {
            if (str.charAt(i) == '?') {
                return this.lenMap.getOrDefault(str.length(), 0);
            }

            int alphabet = str.charAt(i) - 'a';
            return children[alphabet] == null ? 0 : children[alphabet].find(str, i + 1);
        }
    }

    public int[] solution(String[] words, String[] queries) {
        int[] answer = {};

        Trie trie = new Trie();
        Trie reverseTrie = new Trie();
        for (String word : words) {
            trie.insert(word);
            reverseTrie.insert(reverseString(word));
        }

        answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int matchCount = 0;
            if (queries[i].charAt(0) == '?') {
                matchCount = reverseTrie.find(reverseString(queries[i]), 0);
            } else {
                matchCount = trie.find(queries[i], 0);
            }
            answer[i] = matchCount;
        }

        return answer;
    }

    private String reverseString(String word) {
        StringBuilder sb = new StringBuilder();

        return sb.append(word).reverse().toString();
    }

    public static void main(String[] args) {
        Solution3 solution = new Solution3();
        System.out.println(Arrays.toString(
                solution.solution(
                        new String[]{"frodo", "front", "frost", "frozen", "frame", "kakao"},
                        new String[]{"fro??", "????o", "fr???", "fro???", "pro?"})));
    }
}