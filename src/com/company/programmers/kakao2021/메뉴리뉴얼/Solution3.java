package com.company.programmers.kakao2021.메뉴리뉴얼;

import java.util.*;

class Solution3 {
    Map<String, Integer> map = new HashMap<>();

    public String[] solution(String[] orders, int[] course) {
        List<String> resultList = new ArrayList<>();

        for (int c : course) {
            for (String order : orders) {
                if (order.length() < c) {
                    continue;
                }

                combination(order, c, 0, new ArrayList<>());
            }

            int maxCount = 0;
            for (String menu : map.keySet()) {
                maxCount = Math.max(maxCount, map.get(menu));
            }

            if (maxCount < 2) {
                continue;
            }

            for (String menu : map.keySet()) {
                if (map.get(menu) == maxCount) {
                    resultList.add(menu);
                }
            }
            map.clear();
        }

        for (String menu : map.keySet()) {
            if (map.get(menu) >= 2) {
                resultList.add(menu);
            }
        }

        Collections.sort(resultList);
        String[] answer = new String[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            answer[i] = resultList.get(i);
        }

        return answer;
    }

    public void combination(String order, int count, int idx, List<Character> combi) {
        if (combi.size() == count) {
            StringBuilder menu = new StringBuilder();
            List<Character> copyList = new ArrayList<>(combi);
            Collections.sort(copyList);

            for (Character c : copyList) {
                menu.append(c);
            }

            map.put(menu.toString(), map.getOrDefault(menu.toString(), 0) + 1);
            return;
        }

        for (int i = idx; i < order.length(); i++) {
            combi.add(order.charAt(i));
            combination(order, count, i + 1, combi);
            combi.remove(combi.size() - 1);
        }
    }

    public static void main(String[] args) {
        Solution3 solution3 = new Solution3();
//        System.out.println(Arrays.toString(solution3.solution(new String[]{"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"}, new int[]{2, 3, 4})));
        System.out.println(Arrays.toString(solution3.solution(new String[]{"XYZ", "XWY", "WXA"}, new int[]{2, 3, 4})));
    }
}
