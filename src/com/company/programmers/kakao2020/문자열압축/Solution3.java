package com.company.programmers.kakao2020.문자열압축;

import java.util.*;

public class Solution3 {
    public int solution(String s) {
        int answer = 0;

        int minLen = 1000;
        int len = s.length() / 2;
        for (int i = 1; i < len + 1; i++) {
            List<String> splitList = new ArrayList<>();
            for (int j = 0; j < s.length() / i; j++) {
                splitList.add(s.substring(j * i, (j + 1) * i));
            }
            String substring = s.substring(s.length() / i * i, s.length());
            if (!substring.equals("")) {
                splitList.add(substring);
            }

            int idx = 0;
            StringBuilder newS = new StringBuilder();
            while (idx < splitList.size()) {
                String compareS = splitList.get(idx);
                int count = 0;

                idx++;
                while (idx < splitList.size()) {
                    if (compareS.equals(splitList.get(idx))) {
                        idx++;
                        count++;
                    } else {
                        break;
                    }
                }
                if (count == 0) {
                    newS.append(compareS);
                } else {
                    newS.append(count + 1).append(compareS);
                }
            }

            minLen = Math.min(minLen, newS.toString().length());
        }

        if (s.length() == 1) {
            return 1;
        } else {
            return minLen;
        }
    }
}
