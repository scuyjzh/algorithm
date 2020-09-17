package com.scuyjzh.tree;

import java.util.*;

/**
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 *
 * @author scuyjzh
 * @date 2020/9/11 16:12
 */
class ValidateBinarySearchTree {
    /**
     * Approach #1 (Iteration by Inorder Traversal)
     */
    public boolean isValidBST1(TreeNode root) {
        if (root == null) {
            return true;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        TreeNode pre = null;
        while (cur != null || !stack.isEmpty()) {
            // 不断往左子树方向走，每走一次就将当前节点保存到栈中
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            // 当前节点为空，说明左边走到头了，从栈中弹出节点并输出
            cur = stack.pop();
            // 如果当前节点的值小于等于上一个节点的值，说明不是二叉搜索树
            if (pre != null && cur.val <= pre.val) {
                return false;
            }
            // 更新上一个节点
            pre = cur;
            // 然后转向右子树节点，继续上面整个过程
            cur = cur.right;
        }
        return true;
    }

    /**
     * Approach #2 (Recursion)
     */
    public boolean isValidBST2(TreeNode root) {
        /*
            二叉搜索树的两个特征：
            1.节点的左子树只包含小于当前节点的数。
            2.节点的右子树只包含大于当前节点的数。
            也可理解为：
            1.当前节点的值是其左子树的值的上界（最大值）
            2.当前节点的值是其右子树的值的下界（最小值）
        */
        return helper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean helper(TreeNode root, long minVal, long maxVal) {
        if (root == null) {
            return true;
        }
        if (root.val <= minVal || root.val >= maxVal) {
            return false;
        }
        return helper(root.left, minVal, root.val)
                && helper(root.right, root.val, maxVal);
    }

    public static void main(String[] args) {
        ValidateBinarySearchTree solution = new ValidateBinarySearchTree();
        TreeNode root = TreeNode.initBinaryTree("[50,40,80,30,45,60,90,10,35,49,70]");
        System.out.println(solution.isValidBST1(root));
        System.out.println(solution.isValidBST2(root));
    }
}
