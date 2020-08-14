package com.scuyjzh.binarytree;

import java.util.*;

/**
 * @author scuyjzh
 * @date 2020/8/14 2:22
 */
class LeafNumOfBinaryTree {
    /**
     * Approach #1 (Iteration by level-order traversal - BFS)
     */
    public int getLeafNum1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int num = 0;
        while (!queue.isEmpty()) {
            TreeNode curr = queue.remove();
            if (curr.left == null && curr.right == null) {
                num++;
            } else {
                if (curr.left != null) {
                    queue.add(curr.left);
                }
                if (curr.right != null) {
                    queue.add(curr.right);
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
