package com.company.programmers.kakao2021.카드짝맞추기;

import java.util.*;

public class Solution3 {
    static Map<Integer, List<Node>> cardMap = new HashMap<>();
    static int minControlCount = Integer.MAX_VALUE;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static class Node {
        int x;
        int y;
        int count;

        public Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }

    public int solution(int[][] board, int r, int c) {
        int answer = 0;

        Set<Integer> cardSet = new HashSet<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] != 0) {
                    cardSet.add(board[i][j]);
                    if (cardMap.containsKey(board[i][j])) {
                        cardMap.get(board[i][j]).add(new Node(i, j, 0));
                    } else {
                        List<Node> nodeList = new ArrayList<>();
                        nodeList.add(new Node(i, j, 0));
                        cardMap.put(board[i][j], nodeList);
                    }
                }
            }
        }

        List<Integer> cardList = new ArrayList<>(cardSet);
        boolean[] visited = new boolean[cardList.size()];

        findCardOrder(new ArrayList<>(), cardList, visited, r, c, board);

        answer = minControlCount + cardList.size() * 2;

        return answer;
    }

    private void findCardOrder(List<Integer> orders, List<Integer> cardList, boolean[] visited, int r, int c, int[][] board) {
        if (orders.size() == cardList.size()) {
            findMinCount(0, orders, new ArrayList<>(), board, r, c);
            return;
        }

        for (int i = 0; i < cardList.size(); i++) {
            if (visited[i]) {
                continue;
            }

            orders.add(cardList.get(i));
            visited[i] = true;
            findCardOrder(orders, cardList, visited, r, c, board);
            orders.remove(orders.size() - 1);
            visited[i] = false;
        }
    }

    private void findMinCount(int idx, List<Integer> orders, List<Integer> combination, int[][] board, int r, int c) {
        if (idx == orders.size()) {
            int[][] boardTemp = new int[4][4];
            for (int i = 0; i < 4; i++) {
                System.arraycopy(board[i], 0, boardTemp[i], 0, 4);
            }

            int count = 0;
            for (int i = 0; i < orders.size(); i++) {
                List<Node> nodeList = cardMap.get(orders.get(i));
                int first = combination.get(i) == 0 ? 0 : 1;
                int second = combination.get(i) == 0 ? 1 : 0;

                Node firstNode = getCount(r, c, boardTemp, nodeList.get(first));
                Node secondNode = getCount(firstNode.x, firstNode.y, boardTemp, nodeList.get(second));

                boardTemp[firstNode.x][firstNode.y] = 0;
                boardTemp[secondNode.x][secondNode.y] = 0;

                count += firstNode.count;
                count += secondNode.count;

                r = secondNode.x;
                c = secondNode.y;
            }

            minControlCount = Math.min(minControlCount, count);
            return;
        }

        combination.add(0);
        findMinCount(idx + 1, orders, combination, board, r, c);
        combination.remove(combination.size() - 1);
        combination.add(1);
        findMinCount(idx + 1, orders, combination, board, r, c);
        combination.remove(combination.size() - 1);
    }

    private Node getCount(int r, int c, int[][] boardTemp, Node nodeCheck) {
        Node nodeArrive = null;

        Queue<Node> queue = new LinkedList<>();
        boolean[][] passed = new boolean[4][4];

        queue.offer(new Node(r, c, 0));
        passed[r][c] = true;

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if (node.x == nodeCheck.x && node.y == nodeCheck.y) {
                nodeArrive = node;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4) {
                    continue;
                }

                if (!passed[nx][ny]) {
                    passed[nx][ny] = true;
                    queue.offer(new Node(nx, ny, node.count + 1));
                }

                while (boardTemp[nx][ny] == 0) {
                    if ((i == 0 && ny == 3) || (i == 1 && ny == 0) || (i == 2 && nx == 3) || (i == 3 && nx == 0)) {
                        break;
                    }
                    nx += dx[i];
                    ny += dy[i];
                }

                if (!passed[nx][ny]) {
                    passed[nx][ny] = true;
                    queue.offer(new Node(nx, ny, node.count + 1));
                }
            }
        }

        return nodeArrive;
    }

    public static void main(String[] args) {
        Solution3 solution3 = new Solution3();
        System.out.println(solution3.solution(new int[][]{{1,0,0,3},{2,0,0,0},{0,0,0,2},{3,0,1,0}}, 1, 0));

//        Solution3 solution3 = new Solution3();
//        System.out.println(solution3.solution(new int[][]{{3, 0, 0, 2}, {0, 0, 1, 0}, {0, 1, 0, 0}, {2, 0, 0, 3}}, 0, 1));
    }
}