package com.company.programmers.kakao2021.메뉴리뉴얼;

import java.util.*;

public class Solution2 {
    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        List<String> result = new ArrayList<>();

        for (int c : course) {
            int count = 0;
            Map<String, Integer> courseMap = new HashMap<>();
            for (String order : orders) {
                if (order.length() < c) {
                    continue;
                }
                List<Character> courseList = new ArrayList<>();
                findCombination(0, c, order, courseList, courseMap);
            }

            for (String key : courseMap.keySet()) {
                if (count < courseMap.get(key)) {
                    count = courseMap.get(key);
                }
            }

            if (count > 1) {
                for (String key : courseMap.keySet()) {
                    if (count == courseMap.get(key)) {
                        result.add(key);
                    }
                }
            }
        }

        Collections.sort(result);;

        answer = new String[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }

    private void findCombination(int start, int size, String order, List<Character> courseList , Map<String, Integer> courseMap) {
        if (size == courseList.size()) {
            List<Character> courseSortedList = new ArrayList<>(courseList);
            Collections.sort(courseSortedList);

            StringBuilder sb = new StringBuilder();
            for (Character food : courseSortedList) {
                sb.append(food);
            }

            String course = sb.toString();
            courseMap.put(course, courseMap.getOrDefault(course, 0) + 1);

            return;
        }

        for (int i = start; i < order.length(); i++) {
            courseList.add(order.charAt(i));
            findCombination(i + 1, size, order, courseList, courseMap);
            courseList.remove(courseList.size() - 1);
        }
    }

    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
//        System.out.println(Arrays.toString(solution2.solution(new String[]{"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"}, new int[]{2, 3, 4})));
        System.out.println(Arrays.toString(solution2.solution(new String[]{"XYZ", "XWY", "WXA"}, new int[]{2, 3, 4})));
    }
}