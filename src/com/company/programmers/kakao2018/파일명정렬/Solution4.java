package com.company.programmers.kakao2018.파일명정렬;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Solution4 {

    static class HNT {
        String fileName;
        String head;
        int number;
        String tail;

        public HNT(String fileName, String head, int number, String tail) {
            this.fileName = fileName;
            this.head = head;
            this.number = number;
            this.tail = tail;
        }
    }

    public String[] solution(String[] files) {
        String[] answer = {};

        List<HNT> hNTList = new ArrayList<>();
        for (String file : files) {
            int digitStartIdx = findDigitStartIdx(file);
            int digitEndIdx = findDigitEndIdx(digitStartIdx, file);

            String head = file.substring(0, digitStartIdx);
            int number;
            String tail;
            if (digitEndIdx != 0) {
                number = Integer.parseInt(file.substring(digitStartIdx, digitEndIdx));
                tail = file.substring(digitEndIdx);
            } else {
                number = Integer.parseInt(file.substring(digitStartIdx));
                tail = "";
            }

            hNTList.add(new HNT(file, head, number, tail));
        }

        hNTList.sort((o1, o2) -> {
            if (!o1.head.equalsIgnoreCase(o2.head)) {
                return o1.head.toLowerCase().compareTo(o2.head.toLowerCase(Locale.ROOT));
            } else {
                return o1.number - o2.number;
            }
        });

        answer = new String[hNTList.size()];
        for (int i = 0; i < hNTList.size(); i++) {
            answer[i] = hNTList.get(i).fileName;
        }

        return answer;
    }

    private int findDigitEndIdx(int digitStartIdx, String file) {
        int idx = 0;
        for (int i = digitStartIdx + 1; i < file.length(); i++) {
            if (!Character.isDigit(file.charAt(i))) {
                idx = i;
                break;
            }
        }

        if (idx - digitStartIdx > 5) {
            idx = digitStartIdx + 5;
        }

        return idx;
    }

    private int findDigitStartIdx(String file) {
        int idx = 0;
        for (int i = 0; i < file.length(); i++) {
            if (Character.isDigit(file.charAt(i))) {
                idx = i;
                break;
            }
        }

        return idx;
    }

    public static void main(String[] args) {
        Solution4 solution4 = new Solution4();
        System.out.println(Arrays.toString(solution4.solution(new String[]{"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"})));
    }
}