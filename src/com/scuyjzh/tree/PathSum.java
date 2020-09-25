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
                // 累加当前已走路径的和
                curSum += cur.val;
                cur = cur.left;
            }
            // 在出栈前，将栈顶视作实际的根节点，并检查其右子树是否不存在或已被访问
            cur = stack.peek();
            if (cur.left == null && cur.right == null && curSum == sum) {
                // 找到路径
                return true;
            }
            if (cur.right == null || cur.right == pre) {
                // 如果不存在右子树或右子树已被访问，那么可以访问根节点，将其弹出栈
                stack.pop();
                // 减去出栈的值
                curSum -= cur.val;
                // 记录上一个访问的节点，用于判断“访问根节点之前，右子树是否已访问过”
                pre = cur;
                // 表示不需要转向，继续弹栈
                cur = null;
            } else {
                // 如果右子树存在且还未被访问过，就转向其右子树
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
