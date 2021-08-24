package com.company.programmers.kakao2018.파일명정렬;

import java.util.Arrays;

public class Solution {
    public String[] solution(String[] files) {
        String[] answer = {};
        Arrays.sort(files, (o1, o2) -> {
            String[] hNT1 = splitHNT(o1);
            String[] hNT2 = splitHNT(o2);

            if (!hNT1[0].equalsIgnoreCase(hNT2[0])) {
                return hNT1[0].toLowerCase().compareTo(hNT2[0].toLowerCase());
            } else {
                Integer num1 = Integer.parseInt(hNT1[1]);
                Integer num2 = Integer.parseInt(hNT2[1]);
                return num1.compareTo(num2);
            }
        });

        answer = files;

        return answer;
    }

    private String[] splitHNT(String fileName) {
        String[] hNT = new String[3];

        int idx = 0;
        int numStartIdx = 0;
        int numEndIdx = 0;
        while (idx < fileName.length()) {
            if (Character.isDigit(fileName.charAt(idx))) {
                numStartIdx = idx;
                break;
            }
            idx++;
        }

        while (idx < fileName.length()) {
            if (!Character.isDigit(fileName.charAt(idx))) {
                numEndIdx = idx;
                break;
            }
            idx++;
        }

        hNT[0] = fileName.substring(0, numStartIdx);
        if (numEndIdx == 0) {
            hNT[1] = fileName.substring(numStartIdx);
            hNT[2] = "";
        } else {
            hNT[1] = fileName.substring(numStartIdx, numEndIdx);
            hNT[2] = fileName.substring(numEndIdx);
        }

        return hNT;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.solution(new String[]{"foo9.txt", "foo010bar020.zip", "F-15"})));
    }
}