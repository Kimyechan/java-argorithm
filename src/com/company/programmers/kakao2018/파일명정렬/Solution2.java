package com.company.programmers.kakao2018.파일명정렬;

public class Solution2 {
    static TrieNode root = new TrieNode();

    static class TrieNode {
        int[] alphabetCounts = new int[26];
        TrieNode[] children = new TrieNode[26];
    }

    public int solution(String[] words) {
        int answer = 0;
        for (String word : words) {
            insertTrieNode(word);
        }

        int inputCount = 0;
        for (String word : words) {
            inputCount += countAlphabetInput(word);
        }

        answer = inputCount;

        return answer;
    }

    private int countAlphabetInput(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length() - 1; i++) {
            int alphabet = word.charAt(i) - 'a';
            if (node.alphabetCounts[alphabet] == 1) {
                return i + 1;
            }
            node = node.children[alphabet];
        }

        return word.length();
    }

    private void insertTrieNode(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            int alphabet = word.charAt(i) - 'a';
            if (node.children[alphabet] == null) {
                node.children[alphabet] = new TrieNode();
            }
            node.alphabetCounts[alphabet] += 1;
            node = node.children[alphabet];
        }
    }
}