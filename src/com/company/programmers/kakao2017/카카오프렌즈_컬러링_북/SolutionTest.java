package com.company.programmers.kakao2017.카카오프렌즈_컬러링_북;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @Test
    @DisplayName("영역의 갯수와 영역의 크기중 최대 크기")
    public void solution() {
        int m = 6;
        int n = 4;
        int[][] picture = new int[][]{{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}};

        Solution solution = new Solution();
        int[] result = solution.solution(m, n, picture);

        assertEquals(result[0], 4);
        assertEquals(result[1], 5);
    }
}