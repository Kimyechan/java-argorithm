package com.company.programmers.weekly.피로도;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @Test
    public void solution() {
        Solution solution = new Solution();

        int result = solution.solution(80, new int[][]{{80,20},{50,40},{30,10}});

        assertEquals(result, 3);
    }
}