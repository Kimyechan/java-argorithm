package com.company.programmers.problem._2개이하로다른비트;

import java.util.Arrays;

class Solution {
    public long[] solution(long[] numbers) {
        int len = numbers.length;
        long[] answer = new long[len];

        for (int i = 0; i < len; i++) {
            if (numbers[i] % 2 == 0) {
                answer[i] = numbers[i] + 1;
            } else {
                StringBuilder sb = new StringBuilder();
                String binaryString = Long.toBinaryString(numbers[i]);

                if (!binaryString.contains("0")) {
                    sb.append("10");
                    sb.append(binaryString.substring(1));
                } else {
                    int lastZeroIdx = binaryString.lastIndexOf("0");
                    int firstOneIdx = binaryString.indexOf("1", lastZeroIdx);

                    sb.append(binaryString.substring(0, lastZeroIdx));
                    sb.append("1");
                    sb.append("0");
                    sb.append(binaryString.substring(firstOneIdx + 1));
                }

                answer[i] = Long.parseLong(sb.toString(), 2);
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.solution(new long[]{2, 7})));
    }
}
