package com.scuyjzh.binarytree;

import java.util.*;

/**
 * @author scuyjzh
 * @data 2020/8/14 2:10
 */
class NodeNumOfBinaryTree {
    /**
     * Approach #1 (Iteration by level-order traversal - BFS)
     */
    public int getNodeNum1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int num = 1;
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr.left != null) {
                queue.offer(curr.left);
                num++;
            }
            if (curr.right != null) {
                queue.offer(curr.right);
                num++;
            }
        }
        return num;
    }

    /**
     * Approach #2 (Recursion - DFS)
     */
    public int getNodeNum2(TreeNode root) {
        return dfs(root);
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return dfs(root.left) + dfs(root.right) + 1;
        }
    }

    public static void main(String[] args) {
        NodeNumOfBinaryTree solution = new NodeNumOfBinaryTree();
        TreeNode root = TreeNode.initBinaryTree("[3,9,20,null,null,15,7]");
        System.out.println(solution.getNodeNum1(root));
        System.out.println(solution.getNodeNum2(root));
    }
}