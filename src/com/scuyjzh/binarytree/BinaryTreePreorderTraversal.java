package com.scuyjzh.binarytree;

import java.util.*;

/**
 * @author scuyjzh
 * @date 2020/8/14 11:41
 */
class BinaryTreePreorderTraversal {
    /**
     * Approach #1 (Iteration with Stack - DFS)
     */
    public List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        if (root == null) {
            return list;
        }
        // 定义栈模拟二叉树，利用先入后出的特点，出栈顺序为中左右，进栈顺序则为右左中
        Stack<TreeNode> stack = new Stack<>();
        // 先把root压入栈中
        stack.push(root);
        while (!stack.empty()) {
            // 获取栈的顶层节点
            TreeNode cur = stack.pop();
            list.add(cur.val);
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
        return list;
    }

    /**
     * Approach #2 (Iteration by Morris Traversal)
     */
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        if (root == null) {
            return list;
        }
        TreeNode cur = root, pre;
        while (cur != null) {
            if (cur.left == null) {
                list.add(cur.val);
                cur = cur.right;
            } else {
                pre = cur.left;
                while (pre.right != null && pre.right != cur) {
                    pre = pre.right;
                }
                if (pre.right == null) {
                    list.add(cur.val);
                    pre.right = cur;
                    cur = cur.left;
                } else {
                    pre.right = null;
                    cur = cur.right;
                }
            }
        }
        return list;
    }

    /**
     * Approach #3 (Recursion - DFS)
     */
    public List<Integer> preorderTraversal3(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        traversal(root, list);
        return list;
    }

    private void traversal(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        traversal(root.left, list);
        traversal(root.right, list);
    }

    public static void main(String[] args) {
        BinaryTreePreorderTraversal solution = new BinaryTreePreorderTraversal();
        TreeNode root = TreeNode.initBinaryTree("[1,2,3,4,5,6,null]");
        System.out.println(solution.preorderTraversal1(root));
        System.out.println(solution.preorderTraversal2(root));
        System.out.println(solution.preorderTraversal3(root));
    }
}
