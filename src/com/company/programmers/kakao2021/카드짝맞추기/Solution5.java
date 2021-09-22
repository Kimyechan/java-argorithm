package com.company.programmers.kakao2021.카드짝맞추기;

import java.util.*;

class Solution5 {
    static List<Integer> cardList = new ArrayList<>();
    static Map<Integer, List<Card>> map = new HashMap<>();
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int minMoveCount = Integer.MAX_VALUE;

    static class Card {
        int x;
        int y;
        int move;

        public Card(int x, int y, int move) {
            this.x = x;
            this.y = y;
            this.move = move;
        }
    }

    public int solution(int[][] board, int r, int c) {
        int answer = 0;

        Set<Integer> cardSet = new HashSet<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] != 0) {
                    cardSet.add(board[i][j]);
                    if (map.containsKey(board[i][j])) {
                        map.get(board[i][j]).add(new Card(i, j, 0));
                    } else {
                        List<Card> cardTemp = new ArrayList<>();
                        cardTemp.add(new Card(i, j, 0));
                        map.put(board[i][j], cardTemp);
                    }
                }
            }
        }
        cardList.addAll(cardSet);

        boolean[] visited = new boolean[cardList.size()];
        dfs(board, r, c, visited, new ArrayList<>());

        answer = minMoveCount + cardList.size() * 2;

        return answer;
    }

    public void dfs(int[][] board, int r, int c, boolean[] visited, List<Card> orders) {
        if (orders.size() == cardList.size() * 2) {
            int[][] boardTemp = new int[4][4];
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    boardTemp[i][j] = board[i][j];
                }
            }

            int moveCount = 0;
            for (Card order : orders) {
                Queue<Card> q = new LinkedList<>();
                boolean[][] passed = new boolean[4][4];

                q.offer(new Card(r, c, 0));
                passed[r][c] = true;

                while (!q.isEmpty()) {
                    Card card = q.poll();

                    if (card.x == order.x && card.y == order.y) {
                        moveCount += card.move;
                        break;
                    }

                    for (int i = 0; i < 4; i++) {
                        int nx = card.x + dx[i];
                        int ny = card.y + dy[i];

                        if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4) {
                            continue;
                        }

                        if (!passed[nx][ny]) {
                            passed[nx][ny] = true;
                            q.offer(new Card(nx, ny, card.move + 1));
                        }

                        if ((i == 0 || i == 1) && boardTemp[nx][ny] == 0 && ny != 0 && ny != 3) {
                            while (boardTemp[nx][ny] == 0 && ny != 0 && ny != 3) {
                                nx += dx[i];
                                ny += dy[i];
                            }

                            if (!passed[nx][ny]) {
                                passed[nx][ny] = true;
                                q.offer(new Card(nx, ny, card.move + 1));
                            }
                        }
                        if ((i == 2|| i == 3) && boardTemp[nx][ny] == 0 && nx != 0 && nx != 3) {
                            while (boardTemp[nx][ny] == 0 && nx != 0 && nx != 3) {
                                nx += dx[i];
                                ny += dy[i];
                            }

                            if (!passed[nx][ny]) {
                                passed[nx][ny] = true;
                                q.offer(new Card(nx, ny, card.move + 1));
                            }
                        }
                    }
                }

                boardTemp[order.x][order.y] = 0;
                r = order.x;
                c = order.y;
            }

            minMoveCount = Math.min(minMoveCount, moveCount);
            return;
        }


        for (int i = 0; i < cardList.size(); i++) {
            if (visited[i]) {
                continue;
            }

            List<Card> cardTemp = map.get(cardList.get(i));
            for (int j = 0; j < 2; j++) {
                visited[i] = true;
                orders.add(cardTemp.get(1 - j));
                orders.add(cardTemp.get(j));
                dfs(board, r, c, visited, orders);
                orders.remove(orders.size() - 1);
                orders.remove(orders.size() - 1);
                visited[i] = false;
            }
        }
    }
}