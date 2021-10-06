package com.company.leetcode.longest_common_prefix;

class LongestCommonPrefix2 {
    Trie root = new Trie();

    class Trie {
        Trie[] children = new Trie[26];

        public Trie() {
        }

        public void addNode(String str) {
            Trie node = root;
            for (int i = 0; i < str.length(); i++) {
                int alphabet = str.charAt(i) - 'a';
                if (node.children[alphabet] == null) {
                    node.children[alphabet] = new Trie();
                }
                node = node.children[alphabet];
            }
        }

        public int findPrefixMatchLen(String str) {
            Trie node = root;
            int matchLen = 0;
            for (int i = 0; i < str.length(); i++) {
                int alphabet = str.charAt(i) - 'a';
                if (node.children[alphabet] != null) {
                    matchLen += 1;
                    node = node.children[alphabet];
                } else {
                    break;
                }
            }

            return matchLen;
        }
    }

    public String longestCommonPrefix(String[] strs) {
        root.addNode(strs[0]);

        int minLen = strs[0].length();
        for (int i = 1; i < strs.length; i++) {
            minLen = Math.min(minLen, root.findPrefixMatchLen(strs[i]));
        }

        return strs[0].substring(0, minLen);
    }
}
