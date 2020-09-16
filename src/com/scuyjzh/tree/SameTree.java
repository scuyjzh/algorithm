package com.scuyjzh.tree;

import java.util.*;

/**
 * 给定两个二叉树，编写一个函数来检验它们是否相同。
 *
 * @author scuyjzh
 * @date 2020/8/25 10:05
 */
class SameTree {
    /**
     * Approach #1 (Recursion - DFS)
     */
    public boolean isSameTree1(TreeNode p, TreeNode q) {
        return dfs(p, q);
    }

    private boolean dfs(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        return dfs(p.left, q.left) && dfs(p.right, q.right);
    }

    /**
     * Approach #2 (Iteration by Level Order Traversal - BFS)
     */
    public boolean isSameTree2(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        }
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue1.add(p);
        queue2.add(q);
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            TreeNode node1 = queue1.remove();
            TreeNode node2 = queue2.remove();
            if (node1.val != node2.val) {
                return false;
            }
            TreeNode left1 = node1.left, right1 = node1.right, left2 = node2.left, right2 = node2.right;
            if (left1 == null ^ left2 == null) {
                return false;
            }
            if (right1 == null ^ right2 == null) {
                return false;
            }
            if (left1 != null) {
                queue1.add(left1);
            }
            if (right1 != null) {
                queue1.add(right1);
            }
            if (left2 != null) {
                queue2.add(left2);
            }
            if (right2 != null) {
                queue2.add(right2);
            }
        }
        return queue1.isEmpty() && queue2.isEmpty();
    }


    public static void main(String[] args) {
        SameTree solution = new SameTree();
        TreeNode p = TreeNode.initBinaryTree("[1,2,2,3,4,4,3,8,7,6,5,5,6,7,8]");
        TreeNode q = TreeNode.initBinaryTree("[1,2,2,3,4,4,3,8,7,6,5,5,6,7,8]");
        System.out.println(solution.isSameTree1(p, q));
        System.out.println(solution.isSameTree2(p, q));
    }
}
