package com.company.programmers.kakao2021.카드짝맞추기;

import java.util.*;

public class Solution2 {
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
        boolean[] visited = new boolean[7];

        findCardOrder(new ArrayList<>(), cardList, visited, r, c, board);

        answer = minControlCount + cardList.size() * 2;

        return answer;
    }

    private void findCardOrder(List<Integer> orders, List<Integer> cardList, boolean[] visited, int r, int c, int[][] board) {
        if (orders.size() == cardList.size()) {
            int[][] boardTemp  = new int[4][4];
            for (int i = 0; i < 4; i++) {
                System.arraycopy(board[i], 0, boardTemp[i], 0, 4);
            }

            int count = 0;
            for (Integer order : orders) {
                List<Node> nodeList = cardMap.get(order);
                int oneStepCount1 = 0;
                Node firstNode1 = getCount(r, c, boardTemp, nodeList.get(0));
                oneStepCount1 += firstNode1.count;
                Node secondNode1 = getCount(firstNode1.x, firstNode1.y, boardTemp, nodeList.get(1));
                oneStepCount1 += secondNode1.count;

                int oneStepCount2 = 0;
                Node firstNode2 = getCount(r, c, boardTemp, nodeList.get(1));
                oneStepCount2 += firstNode2.count;
                Node secondNode2 = getCount(firstNode2.x, firstNode2.y, boardTemp, nodeList.get(0));
                oneStepCount2 += secondNode2.count;

                if (oneStepCount1 < oneStepCount2) {
                    count += oneStepCount1;
                    boardTemp[firstNode1.x][firstNode1.y] = 0;
                    boardTemp[secondNode1.x][secondNode1.y] = 0;
                    r = secondNode1.x;
                    c = secondNode1.y;
                } else {
                    count += oneStepCount2;
                    boardTemp[firstNode2.x][firstNode2.y] = 0;
                    boardTemp[secondNode2.x][secondNode2.y] = 0;
                    r = secondNode2.x;
                    c = secondNode2.y;
                }
            }

            minControlCount = Math.min(minControlCount, count);

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
//        Solution2 solution2 = new Solution2();
//        System.out.println(solution2.solution(new int[][]{{1,0,0,3},{2,0,0,0},{0,0,0,2},{3,0,1,0}}, 1, 0));

        Solution2 solution21 = new Solution2();
        System.out.println(solution21.solution(new int[][]{{3,0,0,2},{0,0,1,0},{0,1,0,0},{2,0,0,3}}, 0, 1));
    }
}