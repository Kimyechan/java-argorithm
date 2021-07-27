package com.company.baekjun.b13460;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static int minCount = Integer.MAX_VALUE;
    private static String[][] board;
    private static String[][] boardTemp;
    private static Bead red;
    private static Bead blue;
    private static int redX;
    private static int redY;
    private static int blueX;
    private static int blueY;

    static class Bead {
        int x;
        int y;

        public Bead(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        board = new String[n][m];

        for (int i = 0; i < n; i++) {
            input = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                board[i][j] = input[j];
                if (board[i][j].equals("R")) {
                    redX = i;
                    redY = j;
                    red = new Bead(i, j);
                } else if (board[i][j].equals("B")) {
                    blueX = i;
                    blueY = j;
                    blue = new Bead(i, j);
                }
            }
        }

        boardTemp = new String[n][m];
        for (int i = 0; i < n; i++) {
            System.arraycopy(board[i], 0, boardTemp[i], 0, m);
        }

        startGame(new ArrayList<>());

        if (minCount == Integer.MAX_VALUE) {
            System.out.println("-1");
        } else {
            System.out.println(minCount);
        }
    }

    private static void startGame(List<Integer> orders) {
        if (orders.size() == 10) {
            int count = Integer.MAX_VALUE;
            for (int i = 0; i < 10; i++) {
                int direction = orders.get(i);
                int positionRedForBlue = calcRelativeRedPosition(red, blue);

                boolean isRedGoal;
                boolean isBlueGoal;

                if (positionRedForBlue == direction) {
                    isRedGoal = moveBead(red, direction);
                    isBlueGoal = moveBead(blue, direction);
                } else {
                    isBlueGoal = moveBead(blue, direction);
                    isRedGoal = moveBead(red, direction);
                }

                if (isBlueGoal) {
                    break;
                }

                if (isRedGoal) {
                    count = i + 1;
                    break;
                }
            }

            minCount = Math.min(minCount, count);
            initializeGame();
            return;
        }

        for (int i = 1; i <= 4; i++) {
            orders.add(i);
            startGame(orders);
            orders.remove(orders.size() - 1);
        }
    }

    private static void initializeGame() {
        for (int i = 0; i < boardTemp.length; i++) {
            System.arraycopy(boardTemp[i], 0, board[i], 0, boardTemp[i].length);
        }
        red = new Bead(redX, redY);
        blue = new Bead(blueX, blueY);
    }

    private static boolean moveBead(Bead bead, int direction) {
        boolean isGoal = false;
        int dx;
        int dy;

        if (direction == 1) {
            dx = 0;
            dy = -1;
        } else if (direction == 2) {
            dx = -1;
            dy = 0;
        } else if (direction == 3) {
            dx = 0;
            dy = 1;
        } else {
            dx = 1;
            dy = 0;
        }

        while (board[bead.x + dx][bead.y + dy].equals(".") || board[bead.x + dx][bead.y + dy].equals("O")) {
            String temp = board[bead.x][bead.y];
            board[bead.x][bead.y] = ".";
            if (board[bead.x + dx][bead.y + dy].equals("O")) {
                isGoal = true;
                break;
            }
            board[bead.x + dx][bead.y + dy] = temp;
            bead.x = bead.x + dx;
            bead.y = bead.y + dy;
        }

        return isGoal;
    }

    private static int calcRelativeRedPosition(Bead red, Bead blue) {
        if (red.x == blue.x && red.y < blue.y) {
            return 1; // blue 보다 왼쪽
        } else if (red.x == blue.x && red.y > blue.y) {
            return 3; // blue 보다 오른쪽
        } else if (red.y == blue.y && red.x < blue.x) {
            return 2; // blue 보다 위쪽
        } else if (red.y == blue.y && red.x > blue.x) {
            return 4; // blue 보다 아랫쪽
        }
        return 0; // 같은 열 행에 위치하지 않음
    }
}
