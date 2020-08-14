package com.scuyjzh.binarytree;

import java.util.*;

/**
 * @author scuyjzh
 * @date 2020/8/14 2:10
 */
class NodeNumOfBinaryTree {
    /**
     * Approach #1 (Iteration by Level Order Traversal - BFS)
     */
    public int getNodeNum1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int num = 1;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.remove();
            if (cur.left != null) {
                queue.add(cur.left);
                num++;
            }
            if (cur.right != null) {
                queue.add(cur.right);
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
