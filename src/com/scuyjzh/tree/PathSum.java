package com.scuyjzh.tree;

/**
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 *
 * @author scuyjzh
 * @date 2020/9/21 15:10
 */
class PathSum {
    /**
     * Approach #1 (Recursion - DFS)
     */
    public boolean hasPathSum1(TreeNode root, int sum) {
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
        System.out.println(solution.hasPathSum1(root, 22));
        System.out.println(solution.hasPathSum1(root, 23));
    }
}
