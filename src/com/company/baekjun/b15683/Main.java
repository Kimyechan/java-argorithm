package com.company.baekjun.b15683;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static int n;
    private static int m;
    private static int[][] board;
    private static List<CCTV> cctvList = new ArrayList<>();
    private static int minNotViewCount = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        board = new int[n][m];
        for (int i = 0; i < n; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(input[j]);
                if (board[i][j] >= 1 && board[i][j] <= 5) {
                    cctvList.add(new CCTV(i, j, board[i][j]));
                }
            }
        }

        findDirectionCombi(0, new ArrayList<>());

        System.out.println(minNotViewCount);
    }

    private static void findDirectionCombi(int orderNum, List<Integer> directionOrders) {
        if (orderNum == cctvList.size()) {
            int[][] newBoard = markNotViewMap(directionOrders);

            int notViewCount = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (newBoard[i][j] == 0) {
                        notViewCount += 1;
                    }
                }
            }
            minNotViewCount = Math.min(minNotViewCount, notViewCount);
            return;
        }

        for (int dir = 0; dir < 4; dir++) {
            directionOrders.add(dir);
            findDirectionCombi(orderNum + 1, directionOrders);
            directionOrders.remove(directionOrders.size() - 1);
        }
    }

    private static int[][] markNotViewMap(List<Integer> directionOrders) {
        int[][] newBoard = copyBoard();

        for (int i = 0; i < directionOrders.size(); i++) {
            int direction = directionOrders.get(i);
            CCTV cctv = cctvList.get(i);

            if (direction == 0) {
                if (cctv.kind == 1) {
                    markLeftView(cctv, newBoard);
                } else if (cctv.kind == 2) {
                    markLeftView(cctv, newBoard);
                    markRightView(cctv, newBoard);
                } else if (cctv.kind == 3) {
                    markLeftView(cctv, newBoard);
                    markTopView(cctv, newBoard);
                } else if (cctv.kind == 4) {
                    markRightView(cctv, newBoard);
                    markTopView(cctv, newBoard);
                    markBottomView(cctv, newBoard);
                } else {
                    markLeftView(cctv, newBoard);
                    markRightView(cctv, newBoard);
                    markTopView(cctv, newBoard);
                    markBottomView(cctv, newBoard);
                }
            } else if (direction == 1) {
                if (cctv.kind == 1) {
                    markRightView(cctv, newBoard);
                } else if (cctv.kind == 2) {
                    markLeftView(cctv, newBoard);
                    markRightView(cctv, newBoard);
                } else if (cctv.kind == 3) {
                    markRightView(cctv, newBoard);
                    markTopView(cctv, newBoard);
                } else if (cctv.kind == 4) {
                    markLeftView(cctv, newBoard);
                    markTopView(cctv, newBoard);
                    markBottomView(cctv, newBoard);
                } else {
                    markLeftView(cctv, newBoard);
                    markRightView(cctv, newBoard);
                    markTopView(cctv, newBoard);
                    markBottomView(cctv, newBoard);
                }
            } else if (direction == 2) {
                if (cctv.kind == 1) {
                    markBottomView(cctv, newBoard);
                } else if (cctv.kind == 2) {
                    markTopView(cctv, newBoard);
                    markBottomView(cctv, newBoard);
                } else if (cctv.kind == 3) {
                    markLeftView(cctv, newBoard);
                    markBottomView(cctv, newBoard);
                } else if (cctv.kind == 4) {
                    markLeftView(cctv, newBoard);
                    markRightView(cctv, newBoard);
                    markBottomView(cctv, newBoard);
                } else {
                    markLeftView(cctv, newBoard);
                    markRightView(cctv, newBoard);
                    markTopView(cctv, newBoard);
                    markBottomView(cctv, newBoard);
                }
            } else {
                if (cctv.kind == 1) {
                    markTopView(cctv, newBoard);
                } else if (cctv.kind == 2) {
                    markTopView(cctv, newBoard);
                    markBottomView(cctv, newBoard);
                } else if (cctv.kind == 3) {
                    markRightView(cctv, newBoard);
                    markBottomView(cctv, newBoard);
                } else if (cctv.kind == 4) {
                    markLeftView(cctv, newBoard);
                    markRightView(cctv, newBoard);
                    markTopView(cctv, newBoard);
                } else {
                    markLeftView(cctv, newBoard);
                    markRightView(cctv, newBoard);
                    markTopView(cctv, newBoard);
                    markBottomView(cctv, newBoard);
                }
            }
        }

        return newBoard;
    }

    private static void markTopView(CCTV cctv, int[][] boardTemp) {
        int x = cctv.x - 1;
        int y = cctv.y;

        if (x < 0) {
            return;
        }

        while (boardTemp[x][y] >= -1 && boardTemp[x][y] <= 5) {
            if (boardTemp[x][y] == 0) {
                boardTemp[x][y] = -1;
            }
            x -= 1;
            if (x < 0) {
                break;
            }
        }
    }

    private static void markBottomView(CCTV cctv, int[][] boardTemp) {
        int x = cctv.x + 1;
        int y = cctv.y;

        if (x >= n) {
            return;
        }

        while (boardTemp[x][y] >= -1 && boardTemp[x][y] <= 5) {
            if (boardTemp[x][y] == 0) {
                boardTemp[x][y] = -1;
            }
            x += 1;
            if (x >= n) {
                break;
            }
        }
    }

    private static void markLeftView(CCTV cctv, int[][] boardTemp) {
        int x = cctv.x;
        int y = cctv.y - 1;

        if (y < 0) {
            return;
        }

        while (boardTemp[x][y] >= -1 && boardTemp[x][y] <= 5) {
            if (boardTemp[x][y] == 0) {
                boardTemp[x][y] = -1;
            }
            y -= 1;
            if (y < 0) {
                return;
            }
        }
    }

    private static void markRightView(CCTV cctv, int[][] boardTemp) {
        int x = cctv.x;
        int y = cctv.y + 1;

        if (y >= m) {
            return;
        }

        while (boardTemp[x][y] >= -1 && boardTemp[x][y] <= 5) {
            if (boardTemp[x][y] == 0) {
                boardTemp[x][y] = -1;
            }
            y += 1;
            if (y >= m) {
                break;
            }
        }
    }

    private static int[][] copyBoard() {
        int[][] newBoard = new int[n][m];
        for (int i = 0; i < n; i++) {
            System.arraycopy(board[i], 0, newBoard[i], 0, board[i].length);
        }

        return newBoard;
    }

    static class CCTV {
        private int x;
        private int y;
        private int kind;

        public CCTV(int x, int y, int kind) {
            this.x = x;
            this.y = y;
            this.kind = kind;
        }
    }
}
