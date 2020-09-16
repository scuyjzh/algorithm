package com.scuyjzh.binarytree;

import com.scuyjzh.structure.TreeNode;

import java.util.*;

/**
 * 翻转一棵二叉树。
 *
 * @author scuyjzh
 * @date 2020/8/25 10:37
 */
class InvertBinaryTree {
    /**
     * Approach #1 (Recursion - DFS)
     */
    public TreeNode invertTree1(TreeNode root) {
        return invert(root);
    }

    private TreeNode invert(TreeNode root) {
        // 递归函数的终止条件，节点为空时返回
        if (root == null) {
            return null;
        }
        TreeNode right = invert(root.right);
        TreeNode left = invert(root.left);
        root.left = right;
        root.right = left;
        return root;
    }

    /**
     * Approach #2 (Iteration with Queue - BFS)
     */
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) {
            return null;
        }
        // 将二叉树中的节点逐层放入队列中，再迭代处理队列中的元素
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            // 每次都从队列中拿一个节点，并交换这个节点的左右子树
            TreeNode cur = queue.remove();
            TreeNode tmp = cur.left;
            cur.left = cur.right;
            cur.right = tmp;
            // 如果当前节点的左子树不为空，则放入队列等待后续处理
            if (cur.left != null) {
                queue.add(cur.left);
            }
            // 如果当前节点的右子树不为空，则放入队列等待后续处理
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
        // 返回处理完的根节点
        return root;
    }

    public static void main(String[] args) {
        InvertBinaryTree solution = new InvertBinaryTree();
        TreeNode root = TreeNode.initBinaryTree("[1,2,3,4]");
        System.out.println(solution.invertTree1(root));
        System.out.println(solution.invertTree2(root));
    }
}
