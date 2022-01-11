package com.company.programmers.kakao2021.신규아이디추천;

class Solution4 {
    public String solution(String new_id) {
        String result = new_id;

        result = recommendLevel1(result);
        result = recommendLevel2(result);
        result = recommendLevel3(result);
        result = recommendLevel4(result);
        result = recommendLevel5(result);
        result = recommendLevel6(result);
        result = recommendLevel7(result);

        return result;
    }

    private String recommendLevel1(String id) {
        return id.toLowerCase();
    }

    private String recommendLevel2(String id) {
        return id.replaceAll("[^\\w\\.\\-\\_]*", "");
    }

    private String recommendLevel3(String id) {
        return id.replaceAll("\\.+", ".");
    }

    private String recommendLevel4(String id) {
        return id.replaceAll("^\\.|\\.$", "");
    }

    private String recommendLevel5(String id) {
        return id.equals("") ? "a" : id;
    }

    private String recommendLevel6(String id) {
        id = id.length() >= 16 ? id.substring(0, 15) : id;
        return id.replaceAll("\\.$", "");
    }

    private String recommendLevel7(String id) {
        StringBuilder result = new StringBuilder(id);

        while (result.length() < 3) {
            result.append(result.charAt(result.length() - 1));
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Solution4 solution = new Solution4();
        System.out.println(solution.solution("abcdefghijklmn.p"));
    }
}
