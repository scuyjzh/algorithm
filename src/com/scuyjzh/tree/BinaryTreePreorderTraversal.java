package com.scuyjzh.tree;

import java.util.*;

/**
 * 给定一个二叉树，返回它的前序遍历。
 *
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
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                // 每次遍历到“左”节点，立即输出即可
                list.add(cur.val);
                stack.push(cur);
                cur = cur.left;
            }
            // 弹出访问过的“左”节点
            cur = stack.pop();
            // 转向到“右”节点
            cur = cur.right;
        }
        return list;
    }

    /**
     * Approach #2 (Iteration with Stack - DFS)
     */
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        if (root == null) {
            return list;
        }
        // 定义栈模拟二叉树，利用先入后出的特点，节点出栈顺序为根左右，进栈顺序则为右左根
        Deque<TreeNode> stack = new ArrayDeque<>();
        // 先把根节点压入栈中
        stack.push(root);
        while (!stack.isEmpty()) {
            // 弹出栈的顶层节点
            TreeNode cur = stack.pop();
            list.add(cur.val);
            // 进栈：右 -> 左 -> 根
            // 出栈：根 -> 左 -> 右
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
     * Approach #3 (Iteration by Morris Traversal)
     */
    public List<Integer> preorderTraversal3(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        if (root == null) {
            return list;
        }
        TreeNode cur = root, pre;
        while (cur != null) {
            // 1.如果当前节点的左孩子为空，按照中序遍历规则直接输出当前节点，并将其右孩子作为当前节点
            if (cur.left == null) {
                // 输出当前节点
                list.add(cur.val);
                // 将当前节点更新为右孩子
                cur = cur.right;
            }
            // 2.如果当前节点的左孩子不为空，在当前节点的左子树中找到当前节点的前驱节点
            else {
                pre = cur.left;
                while (pre.right != null && pre.right != cur) {
                    // 退出循环的条件是：
                    // (1)pre.right==null，第一次遍历到当前节点，则执行2.a
                    // (2)pre.right==cur，第二次遍历到当前节点，则执行2.b
                    pre = pre.right;
                }
                // 2.a)如果前驱节点的右孩子为空，则将它的右孩子设置为当前节点
                //     同时输出当前节点（在这里输出，这是与中序遍历唯一一点不同）
                //     并将当前节点更新为当前节点的左孩子
                if (pre.right == null) {
                    // 找到当前节点的前驱节点
                    pre.right = cur;
                    // 输出当前节点
                    list.add(cur.val);
                    // 将当前节点更新为左孩子
                    cur = cur.left;
                }
                // 2.b)如果前驱节点的右孩子为当前节点，此时将它的右孩子重新设为空（恢复树的形状）
                //     同时将当前节点更新为当前节点的右孩子
                if (pre.right == cur) {
                    // 恢复树的形状
                    pre.right = null;
                    // 将当前节点更新为右孩子
                    cur = cur.right;
                }
            }
            // 3.重复以上1、2直到当前节点为空
        }
        return list;
    }

    /**
     * Approach #4 (Recursion - DFS)
     */
    public List<Integer> preorderTraversal4(TreeNode root) {
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
        TreeNode root = TreeNode.initBinaryTree("[1,2,7,3,4,null,8,null,null,5,6,null,null,9]");
        System.out.println(solution.preorderTraversal1(root));
        System.out.println(solution.preorderTraversal2(root));
        System.out.println(solution.preorderTraversal3(root));
        System.out.println(solution.preorderTraversal4(root));
    }
}