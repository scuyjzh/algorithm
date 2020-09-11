package com.scuyjzh.binarytree;

import com.scuyjzh.structure.TreeNode;

import java.util.*;

/**
 * @author scuyjzh
 * @date 2020/9/11 16:12
 */
class ValidateBinarySearchTree {
    /**
     * Approach #1 (Iteration by Inorder Traversal)
     */
    public boolean isValidBST1(TreeNode root) {
        if (root == null) {
            return true;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode pre = null;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            if (pre != null && cur.val <= pre.val) {
                return false;
            }
            pre = cur;
            cur = cur.right;
        }
        return true;
    }

    /**
     * Approach #2 (Recursion)
     */
    public boolean isValidBST2(TreeNode root) {
        return helper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean helper(TreeNode root, long minVal, long maxVal) {
        if (root == null) {
            return true;
        }
        if (root.val <= minVal || root.val >= maxVal) {
            return false;
        }
        return helper(root.left, minVal, root.val)
                && helper(root.right, root.val, maxVal);
    }

    public static void main(String[] args) {
        ValidateBinarySearchTree solution = new ValidateBinarySearchTree();
        TreeNode root = TreeNode.initBinaryTree("[50,40,80,30,45,60,90,10,35,49,70]");
        System.out.println(solution.isValidBST2(root));
    }
}
