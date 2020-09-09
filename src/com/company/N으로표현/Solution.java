package com.company.N으로표현;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class Solution {
    public int solution(int N, int number) {
        List<Set<Integer>> sets = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        sets.add(set);
        set = new HashSet<>();
        set.add(N);
        sets.add(set);

        if(N == number){
            return 1;
        }

        for (int i = 1; i <= 8; i++) {
            set = new HashSet<>();
            int num = N;
            for (int j = 1; j < i; j++) {
                num *= 10;
                num += N;
            }
            set.add(num);
            for (int j = 1; j < i / 2 + 1; j++) {
                for (int x: sets.get(j)) {
                    for (int y: sets.get(i - j)) {
                        set.add(x + y);
                        set.add(x * y);
                        set.add(x - y);
                        set.add(y - x);
                        if (y != 0) {
                            set.add(x / y);
                        }
                        if (x != 0) {
                            set.add(y / x);
                        }
                        if(set.contains(number)) {
                            return i;
                        }
                    }
                }
                sets.add(set);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(5, 5));
    }
}
