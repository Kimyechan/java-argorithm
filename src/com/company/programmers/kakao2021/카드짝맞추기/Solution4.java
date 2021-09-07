package com.company.programmers.kakao2021.카드짝맞추기;

import java.util.*;

public class Solution4 {
    static Map<Integer, List<Card>> cardMap = new HashMap<>();
    static int minMoveCount = Integer.MAX_VALUE;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static class Card {
        int x;
        int y;
        int count;

        public Card(int x, int y, int count) {
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
                if (board[i][j] == 0) {
                    continue;
                }

                cardSet.add(board[i][j]);
                if (cardMap.containsKey(board[i][j])) {
                    cardMap.get(board[i][j]).add(new Card(i, j, 0));
                } else {
                    List<Card> cardList = new ArrayList<>();
                    cardList.add(new Card(i, j, 0));
                    cardMap.put(board[i][j], cardList);
                }
            }
        }

        List<Integer> cardNumList = new ArrayList<>(cardSet);
        boolean[] visited = new boolean[cardNumList.size()];

        dfsCardOrder(cardNumList, visited, new ArrayList<>(), board, r, c);

        answer = minMoveCount + cardNumList.size() * 2;

        return answer;
    }

    private void dfsCardOrder(List<Integer> cardNumList, boolean[] visited, List<Integer> numOrders, int[][] board, int r, int c) {
        if (numOrders.size() == cardNumList.size()) {
            dfsSameCardOrder(numOrders, board, r, c, new ArrayList<>());
            return;
        }

        for (int i = 0; i < cardNumList.size(); i++) {
            if (visited[i]) {
                continue;
            }

            numOrders.add(cardNumList.get(i));
            visited[i] = true;
            dfsCardOrder(cardNumList, visited, numOrders, board, r, c);
            numOrders.remove(numOrders.size() - 1);
            visited[i] = false;
        }
    }

    private void dfsSameCardOrder(List<Integer> numOrders, int[][] board, int r, int c, List<Integer> combination) {
        if (combination.size() == numOrders.size()) {
            int[][] boardTemp = new int[4][4];
            for (int i = 0; i < 4; i++) {
                System.arraycopy(board[i], 0, boardTemp[i], 0, 4);
            }

            int count = 0;
            for (int i = 0; i < numOrders.size(); i++) {
                List<Card> cardList = cardMap.get(numOrders.get(i));
                int first = combination.get(i) == 0 ? 0 : 1;
                int second = combination.get(i) == 0 ? 1 : 0;

                Card firstCard = findMinMoveCount(boardTemp, r, c, cardList.get(first));
                count += firstCard.count;
                Card secondCard = findMinMoveCount(boardTemp, firstCard.x, firstCard.y, cardList.get(second));
                count += secondCard.count;

                boardTemp[firstCard.x][firstCard.y] = 0;
                boardTemp[secondCard.x][secondCard.y] = 0;
                r = secondCard.x;
                c = secondCard.y;
            }

            minMoveCount = Math.min(minMoveCount, count);
            return;
        }

        combination.add(0);
        dfsSameCardOrder(numOrders, board, r, c, combination);
        combination.remove(combination.size() - 1);
        combination.add(1);
        dfsSameCardOrder(numOrders, board, r, c, combination);
        combination.remove(combination.size() - 1);
    }

    private Card findMinMoveCount(int[][] boardTemp, int r, int c, Card destination) {
        Card cardArrive = null;

        Queue<Card> queue = new LinkedList<>();
        boolean[][] visited = new boolean[4][4];

        queue.offer(new Card(r, c, 0));
        visited[r][c] = true;

        while (!queue.isEmpty()) {
            Card card = queue.poll();

            if (card.x == destination.x && card.y == destination.y) {
                cardArrive = card;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = card.x + dx[i];
                int ny = card.y + dy[i];

                if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4) {
                    continue;
                }

                if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.offer(new Card(nx, ny, card.count + 1));
                }

                while (boardTemp[nx][ny] == 0) {
                    if ((nx == 3 && i == 0) || (nx == 0 && i == 1) || (ny == 3 && i == 2) || (ny == 0 && i == 3)) {
                        break;
                    }
                    nx += dx[i];
                    ny += dy[i];
                }

                if (!visited[nx][ny]) {
                    queue.offer(new Card(nx, ny, card.count + 1));
                }
            }
        }

        return cardArrive;
    }
}