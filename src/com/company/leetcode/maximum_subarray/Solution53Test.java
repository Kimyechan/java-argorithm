package com.company.leetcode.maximum_subarray;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Solution53Test {

    @Test
    @DisplayName("배열의 연속된 합이 최대가 되는 값 구하기")
    public void maxSubArray() {
        // given
        int[] nums = new int[]{-1, 3, -2, 4, 7};

        // when
        Solution53 solution53 = new Solution53();
        int result = solution53.maxSubArray(nums);

        // then
        assertEquals(result, 12);
    }

}