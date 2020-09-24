package com.scuyjzh.tree;

import java.util.*;

/**
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 *
 * @author scuyjzh
 * @date 2020/9/21 15:10
 */
class PathSum {
    /**
     * Approach #1 (Iteration with Queue - BFS)
     */
    public boolean hasPathSum1(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        Queue<TreeNode> queNode = new LinkedList<>();
        Queue<Integer> queVal = new LinkedList<>();
        queNode.offer(root);
        queVal.offer(root.val);
        while (!queNode.isEmpty()) {
            TreeNode cur = queNode.poll();
            int curSum = queVal.poll();
            if (cur.left == null && cur.right == null) {
                if (curSum == sum) {
                    return true;
                }
                continue;
            }
            if (cur.left != null) {
                queNode.offer(cur.left);
                queVal.offer(cur.left.val + curSum);
            }
            if (cur.right != null) {
                queNode.offer(cur.right);
                queVal.offer(cur.right.val + curSum);
            }
        }
        return false;
    }

    /**
     * Approach #2 (Iteration by Postorder Traversal)
     */
    public boolean hasPathSum2(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        TreeNode pre = null;
        // 记录当前累计的和
        int curSum = 0;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                curSum += cur.val;
                cur = cur.left;
            }
            cur = stack.peek();
            if (cur.left == null && cur.right == null && curSum == sum) {
                return true;
            }
            if (cur.right == null || cur.right == pre) {
                stack.pop();
                // 减去出栈的值
                curSum -= cur.val;
                pre = cur;
                cur = null;
            } else {
                cur = cur.right;
            }
        }
        return false;
    }

    /**
     * Approach #3 (Recursion - DFS)
     */
    public boolean hasPathSum3(TreeNode root, int sum) {
        return dfs(root, sum);
    }

    private boolean dfs(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return sum == root.val;
        }
        return dfs(root.left, sum - root.val) || dfs(root.right, sum - root.val);
    }

    public static void main(String[] args) {
        PathSum solution = new PathSum();
        TreeNode root = TreeNode.initBinaryTree("[5,4,8,11,null,13,4,7,2,null,null,null,null,null,1,null,null,3]");
        System.out.println(solution.hasPathSum1(root, 18));
        System.out.println(solution.hasPathSum2(root, 18));
        System.out.println(solution.hasPathSum3(root, 18));
    }
}
