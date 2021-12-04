package com.company.programmers.kakao_internship_2021.s3;

import java.util.Stack;

public class Solution4 {
    static class Node {
        Node prev;
        Node next;
        boolean isRemoved;
    }

    Stack<Node> stack = new Stack<>();
    Node[] nodes;

    public String solution(int n, int k, String[] cmd) {
        StringBuilder answer = new StringBuilder();
        initNodes(n);

        Node cur = nodes[k];

        for (String c : cmd) {
            String[] command = c.split(" ");
            if (command[0].equals("D")) {
                cur = moveDown(cur, command[1]);
            } else if (command[0].equals("U")) {
                cur = moveUp(cur, command[1]);
            } else if (command[0].equals("C")) {
                Node prevNode = cur.prev;
                Node nextNode = cur.next;

                cur.isRemoved = true;
                stack.push(cur);

                if (prevNode != null) {
                    prevNode.next = nextNode;
                }
                if (nextNode != null) {
                    nextNode.prev = prevNode;
                }

                if (nextNode == null) {
                    cur = prevNode;
                } else {
                    cur = nextNode;
                }
            } else {
                Node renewNode = stack.pop();
                renewNode.isRemoved = false;

                if (renewNode.next != null) {
                    renewNode.next.prev = renewNode;
                }

                if (renewNode.prev != null) {
                    renewNode.prev.next = renewNode;
                }
            }
        }

        for (Node node : nodes) {
            if (node.isRemoved) {
                answer.append("X");
            } else {
                answer.append("O");
            }
        }

        return answer.toString();
    }

    private Node moveDown(Node cur, String count) {
        int move = Integer.parseInt(count);
        for (int i = 0; i < move; i++) {
            if (cur.next == null) {
                break;
            }
            cur = cur.next;
        }
        return cur;
    }

    private Node moveUp(Node cur, String count) {
        int move = Integer.parseInt(count);
        for (int i = 0; i < move; i++) {
            if (cur.prev == null) {
                break;
            }
            cur = cur.prev;
        }
        return cur;
    }

    private void initNodes(int n) {
        nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node();
        }

        for (int i = 0; i < n - 1; i++) {
            nodes[i].next = nodes[i + 1];
            nodes[i + 1].prev = nodes[i];
        }
    }

    public static void main(String[] args) {
        Solution4 solution = new Solution4();
        System.out.println(solution.solution(8, 2, new String[]{"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"}));
//        System.out.println(solution.solution(8, 2, new String[]{"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"}));
    }
}
