package com.company.programmers.kakao2020.괄호변환;

public class Solution2 {
    public String solution(String p) {

        return makeCorrectString(p);
    }

    private String makeCorrectString(String p) {
        if (p.equals("")) {
            return "";
        }
        StringBuilder sb = new StringBuilder();

        int idx = getUIdx(p);

        String u = p.substring(0, idx + 1);
        String v = p.substring(idx + 1);

        if (checkCorrectString(u)) {
            String result = makeCorrectString(v);
            sb.append(u).append(result);
        } else {
            sb.append("(");
            String result = makeCorrectString(v);
            sb.append(result);
            sb.append(")");

            String reverse = reverseString(u.substring(1, u.length() - 1));
            sb.append(reverse);
        }

        return sb.toString();
    }

    private String reverseString(String sub) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < sub.length(); i++) {
            if (sub.charAt(i) == '(') {
                sb.append(")");
            } else {
                sb.append("(");
            }
        }

        return sb.toString();
    }

    private boolean checkCorrectString(String u) {
        int leftCount = 0;
        int rightCount = 0;
        for (int i = 0; i < u.length(); i++) {
            if (u.charAt(i) == '(') {
                leftCount += 1;
            } else {
                rightCount += 1;
            }
            if (leftCount < rightCount) {
                return false;
            }
        }

        return true;
    }

    private int getUIdx(String p) {
        int idx = 0;
        int leftCount = 0;
        int rightCount = 0;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '(') {
                leftCount += 1;
            } else {
                rightCount += 1;
            }
            if (leftCount == rightCount) {
                idx = i;
                break;
            }
        }
        return idx;
    }
}