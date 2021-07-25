package com.company.baekjun.b5052;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main4 {
    private static TrieNode root;

    private static class TrieNode {
        TrieNode[] children = new TrieNode[10];
        Boolean isLasted;

        public TrieNode() {
            this.isLasted = false;
            for (int i = 0; i < 10; i++) {
                this.children[i] = null;
            }
        }
    }

    public static void insert(String key) {
        TrieNode nextTrieNode = root;

        for (int i = 0; i < key.length(); i++) {
            int index = key.charAt(i) - '0';
            if (nextTrieNode.children[index] == null) {
                nextTrieNode.children[index] = new TrieNode();
            }

            nextTrieNode = nextTrieNode.children[index];
        }

        nextTrieNode.isLasted = true;
    }

    public static Boolean available(String key) {
        TrieNode nextTrieNode = root;

        boolean isAvailable = true;
        for (int i = 0; i < key.length() - 1; i++) {
            int index = key.charAt(i) - '0';
            if (nextTrieNode.children[index].isLasted) {
                isAvailable = false;
                break;
            }

            nextTrieNode = nextTrieNode.children[index];
        }

        return isAvailable;
    }

    private static String solution(int n, String[] phoneNumbers) {
        String answer = "YES";

        for (String phoneNumber : phoneNumbers) {
            insert(phoneNumber);
        }

        for (String phoneNumber : phoneNumbers) {
            if (!available(phoneNumber)) {
                answer = "NO";
                break;
            }
        }

        return answer;
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
            String answer = solution(n, phoneNumbers);

            System.out.println(answer);
        }
    }
}
