package com.company.leetcode.maximum_depth_of_binary_tree;


class Solution {
    int count = 0;

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

    public int maxDepth(TreeNode root) {
        findMaxDepth(root, 1);

        return count;
    }

    public void findMaxDepth(TreeNode node, int depth) {
        if (node == null) {
            return;
        }

        count = Math.max(count, depth);

        if (node.left != null) {
            findMaxDepth(node.left, depth + 1);
        }
        if (node.right != null) {
            findMaxDepth(node.right, depth + 1);
        }
    }
}
