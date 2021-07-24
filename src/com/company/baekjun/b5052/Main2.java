package com.company.baekjun.b5052;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2 {
    private static final int COUNT = 10;
    private static TrieNode root;

    static class TrieNode {
        TrieNode[] children = new TrieNode[COUNT];
        boolean isEndOfWord;

        public TrieNode() {
            this.isEndOfWord = false;
            for (int i = 0; i < COUNT; i++) {
                this.children[i] = null;
            }
        }
    }

    public static void insert(String key) {
        TrieNode next = root;

        for (int level = 0; level < key.length(); level++) {
            int index = key.charAt(level) - '0';
            if (next.children[index] == null) {
                next.children[index] = new TrieNode();
            }
            next = next.children[index];
        }

        next.isEndOfWord = true;
    }

    private static boolean checkAvailable(String key) {
        TrieNode next = root;

        for (int level = 0; level < key.length(); level++) {
            int index = key.charAt(level) - '0';
            if (next.isEndOfWord) {
                return true;
            }
            next= next.children[index];
        }

        return false;
    }

    private static String solution(int n, String[] phoneNumbers) {
        for (String phoneNumber : phoneNumbers) {
            insert(phoneNumber);
        }

        for (String phoneNumber : phoneNumbers) {
            if (checkAvailable(phoneNumber)) {
                return "NO";
            }
        }

        return "YES";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());

            String[] phoneNumbers = new String[n];
            for (int j = 0; j < n; j++) {
                phoneNumbers[j] = br.readLine();
            }

            root = new TrieNode();
            String result = solution(n, phoneNumbers);

            System.out.println(result);
        }
    }
}
