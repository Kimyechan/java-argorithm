package com.company.programmers.kakao2020.가사검색;

import java.util.Arrays;

// 효율성 절반만 통과
public class Solution {
    static Trie root = new Trie();

    static class Trie {
        boolean isEndOfWord;
        Trie[] children = new Trie[26];
    }

    public int[] solution(String[] words, String[] queries) {
        int[] answer = {};

        for (String word : words) {
            insertWord(word);
        }

        answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            answer[i] = countMatchWord(root, 0, queries[i]);
        }

        return answer;
    }

    private int countMatchWord(Trie node, int idx, String query) {
        if (node == null) {
            return 0;
        }

        if (idx == query.length()) {
            if (node.isEndOfWord) {
                return 1;
            } else {
                return 0;
            }
        }

        int count = 0;
        if (query.charAt(idx) != '?') {
            int alphabet = query.charAt(idx) - 'a';
            node = node.children[alphabet];
            count += countMatchWord(node, idx + 1, query);
        } else {
            for (int i = 0; i < 26; i++) {
                if (node.children[i] != null) {
                    count += countMatchWord(node.children[i], idx + 1, query);
                }
            }
        }

        return count;
    }

    private void insertWord(String word) {
        Trie node = root;
        for (int i = 0; i < word.length(); i++) {
            int alphabet = word.charAt(i) - 'a';
            if (node.children[alphabet] == null) {
                node.children[alphabet] = new Trie();
            }
            node = node.children[alphabet];
        }

        node.isEndOfWord = true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(
                solution.solution(
                        new String[]{"frodo", "front", "frost", "frozen", "frame", "kakao"},
                        new String[]{"fro??", "????o", "fr???", "fro???", "pro?"})));
    }
}