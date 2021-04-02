package com.company.programmers.kakao2021.신규아이디추천;

public class Solution {
    public String solution(String new_id) {
        String newId = "";
        StringBuilder newIdBuilder = new StringBuilder();

        // 1단계
        newId = new_id.toLowerCase();

        // 2단계
        char[] words2 = newId.toCharArray();
        char[] check = "~!@#$%^&*()=+[{]}:?,<>/".toCharArray();

        for (int i = 0; i < words2.length; i++) {
            int flag = 0;
            for (int j = 0; j < check.length; j++) {
                if (words2[i] == check[j]) {
                    flag = 1;
                    break;
                }
            }

            if (flag == 0) {
                newIdBuilder.append(words2[i]);
            }
        }
        newId = String.valueOf(newIdBuilder);
        newIdBuilder = new StringBuilder();

        // 3단계
        words2 = newId.toCharArray();
        int count = 0;
        for (int i = 0; i < words2.length; i++) {
            if (words2[i] == '.') {
                count += 1;
            } else {
                count = 0;
            }

            if (count < 2) {
                newIdBuilder.append(words2[i]);
            }
        }
        newId = newIdBuilder.toString();

        // 4단계
        words2 = newId.toCharArray();

        if (newId.length() != 0 && words2[words2.length - 1] == '.') {
            newId = newId.substring(0, newId.length() - 1);
        }

        if (newId.length() != 0 && words2[0] == '.') {
            newId = newId.substring(1);
        }

        // 5단계
        if (newId.equals("")) {
            newId = "a";
        }

        // 6단계
        if (newId.length() >= 16) {
            newId = newId.substring(0, 15);
            if (newId.charAt(newId.length() - 1) == '.') {
                newId = newId.substring(0, newId.length() - 1);
            }
        }

        // 7단계
        if (newId.length() <= 2) {
            newIdBuilder = new StringBuilder(newId);
            char lastWord = newIdBuilder.charAt(newIdBuilder.length() - 1);
            while (newIdBuilder.length() < 3) {
                newIdBuilder.append(lastWord);
            }
            newId = newIdBuilder.toString();
        }

        return newId;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution("=.="));
    }
}

class Solution1 {
    public String solution(String new_id) {
        String answer = "";
        String temp = new_id.toLowerCase();

        temp = temp.replaceAll("[^-_.a-z0-9]","");
        temp = temp.replaceAll("[.]{2,}",".");
        temp = temp.replaceAll("^[.]|[.]$","");
        if(temp.equals(""))
            temp+="a";
        if(temp.length() >=16){
            temp = temp.substring(0,15);
            temp=temp.replaceAll("^[.]|[.]$","");
        }
        if(temp.length()<=2)
            while(temp.length()<3)
                temp+=temp.charAt(temp.length()-1);

        answer=temp;
        return answer;
    }
}

class Solution2 {
    public String solution(String new_id) {

        String s = new KAKAOID(new_id)
                .replaceToLowerCase()
                .filter()
                .toSingleDot()
                .noStartEndDot()
                .noBlank()
                .noGreaterThan16()
                .noLessThan2()
                .getResult();

        return s;
    }

    private static class KAKAOID {
        private String s;

        public KAKAOID(String s) {
            this.s = s;
        }


        public KAKAOID replaceToLowerCase() {
            s = s.toLowerCase();
            return this;
        }

        public KAKAOID filter() {
            s = s.replaceAll("[^a-z0-9._-]", "");
            return this;
        }

        public KAKAOID toSingleDot() {
            s = s.replaceAll("[.]{2,}", "");
            return this;
        }

        public KAKAOID noStartEndDot() {
            s = s.replaceAll("^[.][.]$", "");
            return this;
        }

        public KAKAOID noBlank() {
            s = s.isEmpty() ? "a" : s;
            return this;
        }


        public KAKAOID noGreaterThan16() {
            if (s.length() >= 16) {
                s = s.substring(0, 15);
            }
            s = s.replaceAll("[.]$", "");
            return this;
        }


        public KAKAOID noLessThan2() {
            StringBuilder sb = new StringBuilder(s);
            while (sb.length() <= 2) {
                sb.append(sb.charAt(sb.length() - 1));
            }
            s = sb.toString();
            return this;
        }

        public String getResult() {
            return s;
        }
    }
}




