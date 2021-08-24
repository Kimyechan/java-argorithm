package com.company.programmers.kakao2018.자동완성;

public class Solution {
    static TrieNode root = new TrieNode();

    static class TrieNode {
        int[] equalCount = new int[26];
        TrieNode[] children = new TrieNode[26];
    }

    public int solution(String[] words) {
        int answer = 0;

        for (String word : words) {
            insertTrieNode(word);
        }

        int totalInputCount = 0;
        for (String word : words) {
            totalInputCount += countInputAlphabet(word);
        }

        answer = totalInputCount;

        return answer;
    }

    private int countInputAlphabet(String word) {
        TrieNode trieNode = root;
        int len = word.length();

        for (int i = 0; i < len; i++) {
            int alphabet = word.charAt(i) - 'a';
            if (trieNode.equalCount[alphabet] == 1) {
                return i + 1;
            }
            trieNode = trieNode.children[alphabet];
        }

        return len;
    }

    private void insertTrieNode(String word) {
        TrieNode trieNode = root;
        int len = word.length();

        for (int i = 0; i < len; i++) {
            int alphabetIdx = word.charAt(i) - 'a';
            if (trieNode.children[alphabetIdx] == null) {
                trieNode.children[alphabetIdx] = new TrieNode();
            }
            trieNode.equalCount[alphabetIdx] += 1;
            trieNode = trieNode.children[alphabetIdx];
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(new String[]{"go","gone","guild"}));
    }
}