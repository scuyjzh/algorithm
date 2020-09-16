package com.scuyjzh.tree;

import java.util.*;

/**
 * 给定一个二叉树，求出该树的叶子节点个数。
 *
 * @author scuyjzh
 * @date 2020/8/14 2:22
 */
class LeafNumOfBinaryTree {
    /**
     * Approach #1 (Iteration by Level Order Traversal - BFS)
     */
    public int getLeafNum1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int num = 0;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.remove();
            if (cur.left == null && cur.right == null) {
                num++;
            } else {
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
        }
        return num;
    }

    /**
     * Approach #2 (Recursion - DFS)
     */
    public int getLeafNum2(TreeNode root) {
        return dfs(root);
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        return dfs(root.left) + dfs(root.right);
    }

    public static void main(String[] args) {
        LeafNumOfBinaryTree solution = new LeafNumOfBinaryTree();
        TreeNode root = TreeNode.initBinaryTree("[3,9,20,null,null,15,7]");
        System.out.println(solution.getLeafNum1(root));
        System.out.println(solution.getLeafNum2(root));
    }
}
