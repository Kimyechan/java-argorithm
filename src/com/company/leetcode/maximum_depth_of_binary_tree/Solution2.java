package com.company.leetcode.maximum_depth_of_binary_tree;

class Solution2 {
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

    public int maxDepth(TreeNode root) {
        return recursion(root, 0);
    }

    public int recursion(TreeNode node, int depth) {
        if (node == null) {
            return depth;
        }

        return Math.max(recursion(node.left, depth + 1), recursion(node.right, depth + 1));
    }
}
