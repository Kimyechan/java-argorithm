package com.company.programmers.kakao_intershiop_2020.s2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Solution3Test {

    @Test
    @DisplayName("연산자 우선순위 변경시 값 최대값")
    public void solution() {
        String expression = "100-200*300-500+20";

        Solution3 solution3 = new Solution3();
        long result = solution3.solution(expression);

        assertEquals(result, 60420);
    }

}