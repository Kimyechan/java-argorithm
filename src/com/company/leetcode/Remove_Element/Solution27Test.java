package com.company.leetcode.Remove_Element;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Solution27Test {
    @Test
    @DisplayName("특정값 배열에서 제거")
    public void removeElement() {
        // given
        int val = 3;
        int[] nums = new int[]{3, 2, 2, 3};

        // when
        Solution27 solution27 = new Solution27();
        int result = solution27.removeElement(nums, val);

        // then
        assertEquals(result, 2);
        int[] expectedNums = new int[]{2, 2};
        for (int i = 0; i < expectedNums.length; i++) {
            assertEquals(nums[i], expectedNums[i]);
        }
    }

}