package com.company.programmers.kakao2018.자동완성;

public class Solution3 {
    static TrieNode root = new TrieNode();

    static class TrieNode {
        int[] count = new int[26];
        TrieNode[] children = new TrieNode[26];
    }

    public int solution(String[] words) {
        int answer = 0;

        for (String word : words) {
            insertNode(word);
        }

        int inputCount = 0;
        for (String word : words) {
            inputCount += countInputAlphabet(word);
        }

        answer = inputCount;

        return answer;
    }

    private void insertNode(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            int alphabet = word.charAt(i) - 'a';
            if (node.children[alphabet] == null) {
                node.children[alphabet] = new TrieNode();
            }
            node.count[alphabet] += 1;
            node = node.children[alphabet];
        }
    }

    private int countInputAlphabet(String word) {
        int inputCount = 0;
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            int alphabet = word.charAt(i) - 'a';
            inputCount += 1;

            if (node.count[alphabet] == 1) {
                break;
            }

            node = node.children[alphabet];
        }

        return inputCount;
    }

    public static void main(String[] args) {
        Solution3 solution3 = new Solution3();
        System.out.println(solution3.solution(new String[]{"go","gone","guild"}));
    }
}