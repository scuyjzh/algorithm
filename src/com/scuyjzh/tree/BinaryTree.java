package com.scuyjzh.tree;

/**
 * @author scuyjzh
 * @date 2020/8/13 1:34
 */
class BinaryTree {
    /**
     * 求二叉树的深度
     */
    public int depth(TreeNode root) {
        if (root == null) {
            // 递归出口
            return 0;
        } else {
            int leftDepth = depth(root.left);
            int rightDepth = depth(root.right);
            return leftDepth > rightDepth ? leftDepth + 1 : rightDepth + 1;
        }
    }

    /**
     * 求二叉树的节点个数
     */
    public int size(TreeNode root) {
        if (root == null) {
            // 递归出口
            return 0;
        } else {
            int leftSize = size(root.left);
            int rightSize = size(root.right);
            return leftSize + rightSize + 1;
        }
    }
}
