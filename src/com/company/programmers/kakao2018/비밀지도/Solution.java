package com.company.programmers.kakao2018.비밀지도;

import java.util.Arrays;

public class Solution {
    static String[] arrString1;
    static String[] arrString2;

    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = {};

        arrString1 = initializeBinaryString(n, arr1);
        arrString2 = initializeBinaryString(n, arr2);

        answer = new String[n];
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                char a1 = arrString1[i].charAt(j);
                char a2 = arrString2[i].charAt(j);
                if (a1 == '1' || a2 == '1') {
                    sb.append('#');
                    continue;
                }
                sb.append(' ');
            }
            answer[i] = sb.toString();
        }

        return answer;
    }

    private static String[] initializeBinaryString(int n, int[] arr) {
        String[] arrString = new String[n];
        for (int i = 0; i < n; i++) {
            StringBuilder binary = new StringBuilder(Integer.toBinaryString(arr[i]));
            if (binary.length() < n) {
                while (binary.length() < n) {
                    binary.insert(0, "0");
                }
            }
            arrString[i] = binary.toString();
        }

        return arrString;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.solution(5, new int[]{9, 20, 28, 18, 11}, new int[]{30, 1, 21, 17, 28})));
    }
}