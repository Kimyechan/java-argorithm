package com.company.programmers.kakao2020.괄호변환;

public class Solution {
    public String solution(String p) {
        String answer = "";

        answer = makeCorrectString(p);

        return answer;
    }

    private String makeCorrectString(String p) {
        if (p.equals("")) {
            return "";
        }

        int idx = findUVIdx(p);

        String u = p.substring(0, idx + 1);
        String v = p.substring(idx + 1);

        StringBuilder sb = new StringBuilder();

        if (checkCorrectString(u)) {
            sb.append(u);
            String result = makeCorrectString(v);
            sb.append(result);
        } else {
            sb.append("(");
            String result = makeCorrectString(v);
            sb.append(result);
            sb.append(")");

            String reverser = reverseU(u);
            sb.append(reverser);
        }

        return sb.toString();
    }

    private String reverseU(String u) {
        StringBuilder reverser = new StringBuilder();
        for (int i = 1; i < u.length() - 1; i++) {
            if (u.charAt(i) == '(') {
                reverser.append(')');
            } else {
                reverser.append('(');
            }
        }

        return reverser.toString();
    }

    private int findUVIdx(String p) {
        int leftCount = 0;
        int rightCount = 0;
        int idx = 0;
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

    private boolean checkCorrectString(String s) {
        int leftCount = 0;
        int rightCount = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
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

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution("()))((()"));
    }
}