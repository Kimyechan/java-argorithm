package com.company.leetcode.Merge_Sorted_Array;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Solution88Test {
    @Test
    @DisplayName("두 개의 오름차순 배열을 합친 오름차순 배열 만들기")
    public void merge() {
//        Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
//        Output: [1,2,2,3,5,6]
        // given
        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0};
        int[] nums2 = new int[]{2, 5, 6};

        // when
        Solution88 solution88 = new Solution88();
        solution88.merge(nums1, 3, nums2, 3);

        int[] expected = new int[]{1, 2, 2, 3, 5, 6};
        for (int i = 0; i < nums1.length; i++) {
            assertEquals(nums1[i], expected[i]);
        }
    }

    @Test
    @DisplayName("두 개의 오름차순 배열을 합친 오름차순 배열 만들기2")
    public void merge2() {
//        Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
//        Output: [1,2,2,3,5,6]
        // given
        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0};
        int[] nums2 = new int[]{2, 5, 6};

        // when
        Solution88_2 solution88 = new Solution88_2();
        solution88.merge(nums1, 3, nums2, 3);

        int[] expected = new int[]{1, 2, 2, 3, 5, 6};
        for (int i = 0; i < nums1.length; i++) {
            assertEquals(nums1[i], expected[i]);
        }
    }
}