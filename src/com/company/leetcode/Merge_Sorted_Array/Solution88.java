package com.company.leetcode.Merge_Sorted_Array;

public class Solution88 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] temp = new int[m + n];

        int idx1 = 0;
        int idx2 = 0;
        int idx = 0;
        while (idx1 < m || idx2 < n) {
            if (idx1 == m) {
                for (int i = idx; i < m + n; i++) {
                    temp[i] = nums2[idx2];
                    idx2++;
                }
                break;
            }
            if (idx2 == n) {
                for (int i = idx; i < m + n; i++) {
                    temp[i] = nums1[idx1];
                    idx1++;
                }
                break;
            }

            if (nums1[idx1] < nums2[idx2]) {
                temp[idx] = nums1[idx1];
                idx++;
                idx1++;
            } else {
                temp[idx] = nums2[idx2];
                idx++;
                idx2++;
            }
        }

        for (int i = 0; i < m + n; i++) {
            nums1[i] = temp[i];
        }
    }
}
