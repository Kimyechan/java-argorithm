package com.company.programmers.kakao2020.가사검색;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution5 {

    static class Trie {
        Map<Integer, Integer> lenMap = new HashMap<>();
        Trie[] children = new Trie[26];

        public void insert(String word) {
            Trie node = this;
            for (int i = 0; i < word.length(); i++) {
                int alphabet = word.charAt(i) - 'a';
                if (node.children[alphabet] == null) {
                    node.children[alphabet] = new Trie();
                }
                node.lenMap.put(word.length(), node.lenMap.getOrDefault(word.length(), 0) + 1);
                node = node.children[alphabet];
            }
        }

        public int search(String query) {
            Trie node = this;
            for (int i = 0; i < query.length(); i++) {
                if (query.charAt(i) == '?') {
                    return node.lenMap.getOrDefault(query.length(), 0);
                } else {
                    int alphabet = query.charAt(i) - 'a';
                    node = node.children[alphabet];
                    if (node == null) {
                        return 0;
                    }
                }
            }

            return 0;
        }
    }

    public int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];

        Trie trieOrder = new Trie();
        Trie trieReverse = new Trie();
        for (String word : words) {
            trieOrder.insert(word);
            trieReverse.insert(reverseString(word));
        }

        for (int i = 0; i < queries.length; i++) {
            String query = queries[i];
            int count = 0;
            if (query.charAt(0) == '?') {
                count = trieReverse.search(reverseString(query));
            } else {
                count = trieOrder.search(query);
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
        Solution5 solution = new Solution5();
        System.out.println(Arrays.toString(
                solution.solution(
                        new String[]{"frodo", "front", "frost", "frozen", "frame", "kakao"},
                        new String[]{"fro??", "????o", "fr???", "fro???", "pro?"})));
    }
}