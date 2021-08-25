package com.company.programmers.kakao2018.파일명정렬;

import java.util.Arrays;
import java.util.Locale;

public class Solution3 {
    public String[] solution(String[] files) {
        String[] answer = {};
        Arrays.sort(files, (f1, f2) -> {
            String[] hNT1 = splitToHNT(f1);
            String[] hNT2 = splitToHNT(f2);

            if (!hNT1[0].equalsIgnoreCase(hNT2[0])) {
                return hNT1[0].toLowerCase().compareTo(hNT2[0].toLowerCase(Locale.ROOT));
            } else {
                return Integer.parseInt(hNT1[1]) - Integer.parseInt(hNT2[1]);
            }
        });

        answer = files;

        return answer;
    }

    private String[] splitToHNT(String fileName) {
        String[] hNT = new String[3];

        int startNumIdx = 0;
        int idx = 0;
        while (idx < fileName.length()) {
            if (Character.isDigit(fileName.charAt(idx))) {
                startNumIdx = idx;
                break;
            }
            idx++;
        }

        int endNumIdx = 0;
        while (idx < fileName.length()) {
            if (!Character.isDigit(fileName.charAt(idx))) {
                if (idx - startNumIdx > 5) {
                    endNumIdx = startNumIdx + 5;
                } else {
                    endNumIdx = idx;
                }
                break;
            }
            idx++;
        }

        if (endNumIdx == 0) {
            hNT[0] = fileName.substring(0, startNumIdx);
            hNT[1] = fileName.substring(startNumIdx);
            hNT[2] = "";
        } else {
            hNT[0] = fileName.substring(0, startNumIdx);
            hNT[1] = fileName.substring(startNumIdx, endNumIdx);
            hNT[2] = fileName.substring(endNumIdx);
        }

        return hNT;
    }
}