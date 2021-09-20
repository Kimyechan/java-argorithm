package com.company.programmers.kakao2020.괄호변환;

public class Solution3 {
    public String solution(String p) {
        String answer = "";
        if (checkCorrect(p)) {
            answer = p;
        } else {
            answer = makeCorrect(p);
        }

        return answer;
    }

    public String makeCorrect(String p) {
        if (p.equals("")) {
            return p;
        }

        int leftCount = 0;
        int rightCount = 0;
        int idx = 0;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '(')  {
                leftCount++;
            }
            if (p.charAt(i) == ')') {
                rightCount++;
            }

            if (leftCount == rightCount) {
                break;
            }
            idx++;
        }

        String u = p.substring(0, idx + 1);
        String v = p.substring(idx + 1);

        StringBuilder sb = new StringBuilder();
        if (checkCorrect(u)) {
            sb.append(u).append(makeCorrect(v));
        } else {
            sb.append("(").append(makeCorrect(v)).append(")").append(reverseString(u.substring(1, u.length() - 1)));
        }

        return sb.toString();
    }

    public boolean checkCorrect(String p) {
        boolean result = true;

        int leftCount = 0;
        int rightCount = 0;

        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '(')  {
                leftCount++;
            }
            if (p.charAt(i) == ')') {
                rightCount++;
            }

            if (leftCount < rightCount) {
                result = false;
                break;
            }
        }

        return result;
    }

    public String reverseString(String u) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < u.length(); i++) {
            if (u.charAt(i) == '(')  {
                sb.append(")");
            }
            if (u.charAt(i) == ')') {
                sb.append("(");
            }
        }

        return sb.toString();
    }
}
