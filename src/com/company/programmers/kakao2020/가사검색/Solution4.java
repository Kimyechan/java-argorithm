package com.company.programmers.kakao2020.가사검색;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution4 {

    static class Trie {
        Map<Integer, Integer> lenMap = new HashMap<>();
        Trie[] children = new Trie[26];

        public void insert(String word) {
            Trie node = this;
            int len = word.length();

            for (int i = 0; i < len; i++) {
                int idx = word.charAt(i) - 'a';
                if (node.children[idx] == null) {
                    node.children[idx] = new Trie();
                }
                node.lenMap.put(len, node.lenMap.getOrDefault(len, 0) + 1);
                node = node.children[idx];
            }
        }

        public int search(int idx, String query) {
            if (query.charAt(idx) == '?') {
                return this.lenMap.getOrDefault(query.length(), 0);
            }

            int alphabet = query.charAt(idx) - 'a';
            return this.children[alphabet] == null ? 0 : this.children[alphabet].search(idx + 1, query);
        }
    }

    public int[] solution(String[] words, String[] queries) {
        int[] answer = {};

        Trie right = new Trie();
        Trie left = new Trie();
        for (String word : words) {
            right.insert(word);
            left.insert(reverseString(word));
        }

        answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int count = 0;
            if (queries[i].charAt(0) == '?') {
                count = left.search(0, reverseString(queries[i]));
            } else {
                count = right.search(0, queries[i]);
            }
            answer[i] = count;
        }

        return answer;
    }

    private String reverseString(String word) {
        StringBuilder sb = new StringBuilder();

        return sb.append(word).reverse().toString();
    }

    public static void main(String[] args) {
        Solution4 solution = new Solution4();
        System.out.println(Arrays.toString(
                solution.solution(
                        new String[]{"frodo", "front", "frost", "frozen", "frame", "kakao"},
                        new String[]{"fro??", "????o", "fr???", "fro???", "pro?"})));
    }
}