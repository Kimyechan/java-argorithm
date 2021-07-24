package com.company.baekjun.b5052;

public class Trie {
    static final int ALPHABET = 26;
    static TrieNode root;

    static class TrieNode {
        TrieNode[] children = new TrieNode[ALPHABET];
        boolean isEndOfWord;

        public TrieNode() {
            this.isEndOfWord = false;
            for (int i = 0; i < ALPHABET; i++) {
                this.children[i] = null;
            }
        }
    }

    static void insert(String key) {
        int length = key.length();
        int index;

        TrieNode pCrawl = root;
        for (int level = 0; level < length; level++) {
            index = key.charAt(level) - 'a';
            if (pCrawl.children[index] == null) {
                pCrawl.children[index] = new TrieNode();
            }
            pCrawl = pCrawl.children[index];
        }

        pCrawl.isEndOfWord = true;
    }

    static boolean search(String key) {
        int length = key.length();
        int index;
        TrieNode pCrawl = root;

        for (int level = 0; level < length; level++) {
            index = key.charAt(level) - 'a';
            if (pCrawl.children[index] == null) {
                return false;
            }
            pCrawl = pCrawl.children[index];
        }

        return (pCrawl != null && pCrawl.isEndOfWord);
    }
}
