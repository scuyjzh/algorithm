package com.scuyjzh.tree;

/**
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 *
 * @author scuyjzh
 * @date 2020/8/25 11:00
 */
class BalancedBinaryTree {
    /**
     * 方法一：自顶向下
     */
    public boolean isBalanced1(TreeNode root) {
        return helper(root);
    }

    private boolean helper(TreeNode root) {
        if (root == null) {
            return true;
        }
        return Math.abs(depth(root.left) - depth(root.right)) <= 1 && helper(root.left) && helper(root.right);
    }

    private int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(depth(root.left), depth(root.right));
    }

    /**
     * 方法二：自底向上
     */
    public boolean isBalanced2(TreeNode root) {
        // 若 recur(root) != -1 ，则说明此树平衡，返回 true；否则返回 false
        return recur(root) != -1;
    }

    private int recur(TreeNode root) {
        // 递归终止条件：
        // 1.当越过叶子结点时，返回高度 0
        // 2.当左（右）子树高度 left == -1 时，代表此子树的左（右）子树不是平衡树，因此直接返回 -1
        if (root == null) {
            return 0;
        }
        int left = recur(root.left);
        if (left == -1) {
            return -1;
        }
        int right = recur(root.right);
        if (right == -1) {
            return -1;
        }
        // 递归返回值：
        // 1.当结点 root 左/右子树的高度差 < 2：则返回以结点root为根结点的子树的最大高度，即结点 root 的左右子树中最大高度加 1 (max(left, right) + 1)
        // 2.当结点 root 左/右子树的高度差 ≥ 2：则返回 -1，代表此子树不是平衡树
        return Math.abs(left - right) <= 1 ? Math.max(left, right) + 1 : -1;
    }

    public static void main(String[] args) {
        BalancedBinaryTree solution = new BalancedBinaryTree();
        TreeNode root = TreeNode.initBinaryTree("[1,2,2,3,4,4,3,8,7,6,5,5,6,7,8]");
        System.out.println(solution.isBalanced1(root));
        System.out.println(solution.isBalanced2(root));
    }
}
