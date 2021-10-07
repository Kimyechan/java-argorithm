package com.company.programmers.kakao_intershiop_2020.s1;

class Solution {

    static class Hand {
        int x;
        int y;

        public Hand(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public String solution(int[] numbers, String hand) {
        StringBuilder sb = new StringBuilder();

        int[][] spots = initSpots();
        Hand leftHand = new Hand(3, 0);
        Hand rightHand = new Hand(3, 2);

        for (int number : numbers) {
            int row = spots[number][0];
            int col = spots[number][1];

            if (number % 3 == 1) {
                sb.append("L");
                pickHand(sb, leftHand, row, col);
                continue;
            }

            if (number % 3 == 0 && number != 0) {
                sb.append("R");
                pickHand(sb, rightHand, row, col);
                continue;
            }

            int leftDis = Math.abs(leftHand.x - row) + Math.abs(leftHand.y - col);
            int rightDis = Math.abs(rightHand.x - row) + Math.abs(rightHand.y - col);

            if (leftDis < rightDis) {
                sb.append("L");
                pickHand(sb, leftHand, row, col);
            } else if (leftDis > rightDis) {
                sb.append("R");
                pickHand(sb, rightHand, row, col);
            } else {
                if (hand.equals("left")) {
                    sb.append("L");
                    pickHand(sb, leftHand, row, col);
                } else {
                    sb.append("R");
                    pickHand(sb, rightHand, row, col);
                }
            }
        }


        return sb.toString();
    }

    private void pickHand(StringBuilder sb, Hand hand, int row, int col) {
        hand.x = row;
        hand.y = col;
    }

    public int[][] initSpots() {
        int[][] spots = new int[10][2];
        spots[0] = new int[]{3, 1};
        for (int i = 0; i < 9; i++) {
            spots[i + 1] = new int[]{i / 3, i % 3};

        }

        return spots;
    }
}
