package com.company.programmers.kakao2020.가사검색;

import java.util.*;

public class Solution6 {

    static class Trie {
        Map<Integer, Integer> lenMap = new HashMap<>();
        Trie[] children = new Trie[26];

        public void add(String s) {
            Trie node = this;
            for (int i = 0; i < s.length(); i++) {
                int alphabet = s.charAt(i) - 'a';
                if (node.children[alphabet] == null) {
                    node.children[alphabet] = new Trie();
                }
                node.lenMap.put(s.length(), node.lenMap.getOrDefault(s.length(), 0) + 1);
                node = node.children[alphabet];
            }
            node.lenMap.put(s.length(), node.lenMap.getOrDefault(s.length(), 0) + 1);
        }

        public int find(String query) {
            Trie node = this;
            for (int i = 0; i < query.length(); i++) {
                if (query.charAt(i) == '?') {
                    break;
                }
                int alphabet = query.charAt(i) - 'a';
                if (node.children[alphabet] == null) {
                    return 0;
                }
                node = node.children[alphabet];
            }

            return node.lenMap.getOrDefault(query.length(), 0);
        }

        public int find2(String query, int idx) {
            if (query.charAt(idx) == '?') {
                return lenMap.getOrDefault(query.length(), 0);
            }

            int alphabet = query.charAt(idx) - 'a';
            return children[alphabet] == null ? 0 : children[alphabet].find2(query, idx + 1);
        }
    }

    public int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];

        Trie trie = new Trie();
        Trie reverseTrie = new Trie();
        for (String word : words) {
            trie.add(word);
            reverseTrie.add(reverse(word));
        }

        for (int i = 0; i < queries.length; i++) {
            if (queries[i].charAt(0) == '?') {
                answer[i] = reverseTrie.find(reverse(queries[i]));
            } else {
                answer[i] = trie.find(queries[i]);
            }
        }

        return answer;
    }

    public String reverse(String s) {
        StringBuilder sb = new StringBuilder();

        return sb.append(s).reverse().toString();
    }
}
