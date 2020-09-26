package com.scuyjzh.tree;

import java.util.*;

/**
 * 给定一个二叉树，原地将它展开为一个单链表。
 *
 * @author scuyjzh
 * @date 2020/9/25 11:09
 */
class FlattenBinaryTreeToLinkedList {
    /**
     * 方法一：前序遍历
     */
    public void flatten1(TreeNode root) {
        List<TreeNode> list = new LinkedList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        // 对二叉树进行前序遍历，获得各节点被访问到的顺序
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                list.add(cur);
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            cur = cur.right;
        }
        // 更新每个节点的左右子节点的信息，将二叉树展开为单链表
        int size = list.size();
        for (int i = 1; i < size; i++) {
            TreeNode prev = list.get(i - 1), curr = list.get(i);
            prev.left = null;
            prev.right = curr;
        }
    }

    /**
     * 方法二：前序遍历和展开同步进行
     */
    public void flatten2(TreeNode root) {
        if (root == null) {
            return;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        TreeNode pre = null;
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if (pre != null) {
                pre.left = null;
                pre.right = cur;
            }
            TreeNode left = cur.left, right = cur.right;
            if (right != null) {
                stack.push(right);
            }
            if (left != null) {
                stack.push(left);
            }
            pre = cur;
        }
    }
}
