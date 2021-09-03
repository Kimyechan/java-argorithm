package com.company.programmers.kakao2021.신규아이디추천;

public class Solution3 {
    public String solution(String new_id) {
        String answer = "";

        new_id = apply1Step(new_id);
        new_id = apply2Step(new_id);
        new_id = apply3Step(new_id);
        new_id = apply4Step(new_id);
        new_id = apply5Step(new_id);
        new_id = apply6Step(new_id);
        new_id = apply7Step(new_id);

        return new_id;
    }

    private String apply1Step(String newId) {
        return newId.toLowerCase();
    }

    private String apply2Step(String new_id) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < new_id.length(); i++) {
            char c = new_id.charAt(i);
            if (Character.isAlphabetic(c) || Character.isDigit(c) || c == '-' || c == '_' || c == '.') {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    private String apply3Step(String new_id) {
        return new_id.replaceAll("\\.+", ".");
    }


    private String apply4Step(String new_id) {
        if (new_id.length() >= 1 && new_id.charAt(0) == '.') {
            new_id = new_id.substring(1);
        }

        if (new_id.length() >= 1 && new_id.charAt(new_id.length() - 1) == '.') {
            new_id = new_id.substring(0, new_id.length() - 1);
        }

        return new_id;
    }

    private String apply5Step(String new_id) {
        if (new_id.equals("")) {
            return "a";
        }
        return new_id;
    }

    private String apply6Step(String new_id) {
        if (new_id.length() >= 16) {
            new_id = new_id.substring(0, 15);
            if (new_id.charAt(new_id.length() - 1) == '.') {
                new_id = new_id.substring(0, new_id.length() - 1);
            }
        }

        return new_id;
    }

    private String apply7Step(String new_id) {
        StringBuilder sb = new StringBuilder();
        sb.append(new_id);

        if (sb.length() <= 2) {
            char c = sb.charAt(sb.length() - 1);
            while (sb.length() < 3) {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Solution3 solution3 = new Solution3();
        System.out.println(solution3.solution("...!@BaT#*..y.abcdefghijklm"));
    }
}