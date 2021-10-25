package com.company.leetcode.convert_sorted_array_to_binary_search_tree;

class Solution {

    class TreeNode {
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

    public TreeNode sortedArrayToBST(int[] nums) {
        int len = nums.length;

        int center = len / 2;
        TreeNode root = new TreeNode(nums[center]);
        addLeft(root, 0, center - 1, nums);
        addRight(root, center + 1, len - 1, nums);

        return root;
    }

    public void addLeft(TreeNode root, int left, int right, int[] nums) {
        if (left > right) {
            return;
        }

        int mid = (left + right) / 2;
        root.left = new TreeNode(nums[mid]);

        addLeft(root.left, left, mid - 1, nums);
        addRight(root.left, mid + 1, right, nums);
    }

    public void addRight(TreeNode root, int left, int right, int[] nums) {
        if (left > right) {
            return;
        }

        int mid = (left + right) / 2;
        root.right = new TreeNode(nums[mid]);

        addLeft(root.right, left, mid - 1, nums);
        addRight(root.right, mid + 1, right, nums);
    }
}
