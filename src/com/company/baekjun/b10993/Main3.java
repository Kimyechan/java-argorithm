package com.company.baekjun.b10993;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main3 {
    static char[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int width = 1;
        int height = 1;
        int four = 4;
        int two = 2;

        for (int i = 0; i < n - 1; i++) {
            width += four;
            four *= 2;
        }

        for (int i = 0; i < n - 1; i++) {
            height += two;
            two *= 2;
        }

        board = new char[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = ' ';
            }
        }

        drawStar(0, 0, width, height, n);

        StringBuilder sb = new StringBuilder();
        if (n % 2 == 1) {
            int k = 0;
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width / 2 + 1 + k; j++) {
                    sb.append(board[i][j]);
                }
                sb.append("\n");
                k++;
            }
        } else {
            int k = 0;
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width - k; j++) {
                    sb.append(board[i][j]);
                }
                sb.append("\n");
                k++;
            }
        }

        System.out.println(sb.toString());
    }

    private static void drawStar(int w, int h, int wLen, int hLen, int seq) {
        if (seq <= 0) {
            return;
        }

        int left = w;
        int right = w + wLen;
        int mid = (w + wLen + w) / 2;

        if (seq % 2 == 1) {
            int leftIdx = mid;
            int rightIdx = mid;
            for (int i = h; i < h + hLen; i++) {
                for (int j = leftIdx; j <= rightIdx; j++) {
                    if (i == h || i == h + hLen - 1 || j == leftIdx || j == rightIdx) {
                        board[i][j] = '*';
                    }
                }
                leftIdx--;
                rightIdx++;
            }
        } else {
            int leftIdx = left;
            int rightIdx = right;
            for (int i = h; i < h + hLen; i++) {
                for (int j = leftIdx; j < rightIdx; j++) {
                    if (i == h || i == h + hLen - 1 || j == leftIdx || j == rightIdx - 1) {
                        board[i][j] = '*';
                    }
                }
                leftIdx++;
                rightIdx--;
            }
        }

        int moveRight = w + (int) Math.pow(2, seq - 1);
        if ((seq - 1) % 2 == 1) {
            drawStar(moveRight, h + 1, wLen - (int) Math.pow(2, seq), hLen / 2, seq - 1);
        } else {
            drawStar(moveRight, h + hLen / 2, wLen - (int) Math.pow(2, seq), hLen / 2, seq - 1);
        }
    }
}