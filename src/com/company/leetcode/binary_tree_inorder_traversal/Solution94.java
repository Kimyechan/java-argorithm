package com.company.leetcode.binary_tree_inorder_traversal;

import java.util.ArrayList;
import java.util.List;

public class Solution94 {
    List<Integer> inorderList = new ArrayList<>();

    public List<Integer> inorderTraversal(TreeNode root) {
        inorder(root);

        return inorderList;
    }

    public void inorder(TreeNode root) {
        if (root == null) {
            return;
        }

        inorder(root.left);
        inorderList.add(root.val);
        inorder(root.right);
    }
}
