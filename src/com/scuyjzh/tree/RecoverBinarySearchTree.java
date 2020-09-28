package com.scuyjzh.tree;

import java.util.*;

/**
 * 二叉搜索树中的两个结点被错误地交换。请在不改变其结构的情况下，恢复这棵树。
 *
 * @author scuyjzh
 * @date 2020/9/18 9:52
 */
class RecoverBinarySearchTree {
    public void recoverTree(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode firstNode = null;
        TreeNode secondNode = null;
        TreeNode pre = new TreeNode(Integer.MIN_VALUE);
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            if (firstNode == null && pre.val > cur.val) {
                firstNode = pre;
            }
            if (firstNode != null && pre.val > cur.val) {
                secondNode = cur;
            }
            pre = cur;
            cur = cur.right;
        }
        int tmp = firstNode.val;
        firstNode.val = secondNode.val;
        secondNode.val = tmp;
    }

    public static void main(String[] args) {
        RecoverBinarySearchTree solution = new RecoverBinarySearchTree();
        TreeNode root = TreeNode.initBinaryTree("[3,4,1,null,2]");
        solution.recoverTree(root);
    }
}
