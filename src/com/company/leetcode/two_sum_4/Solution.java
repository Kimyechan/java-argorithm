package com.company.leetcode.two_sum_4;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    List<Integer> inorderList = new ArrayList<>();

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public boolean findTarget(TreeNode root, int k) {
        makeInorder(root);

        boolean result = false;
        int start = 0;
        int end = inorderList.size() - 1;
        while (start < end) {
            int sum = inorderList.get(start) + inorderList.get(end);

            if (sum > k) {
                end--;
            } else if (sum < k) {
                start++;
            } else {
                result = true;
                break;
            }
        }

        return result;
    }

    public void makeInorder(TreeNode root) {
        if (root == null) {
            return;
        }

        makeInorder(root.left);
        inorderList.add(root.val);
        makeInorder(root.right);
    }
}
