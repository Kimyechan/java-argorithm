package com.company.leetcode.search_index_position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Solution35Test {

    @Test
    @DisplayName("정렬된 배열에서 해당 값을 찾거나 들어갈 인덱스 찾기")
    public void searchInsert() {
        // given
        int[] nums = new int[]{1, 4, 6, 7};
        int target = 4;

        Solution35 solution35 = new Solution35();
        int result = solution35.searchInsert(nums, target);

        assertEquals(result, 1);
    }

}