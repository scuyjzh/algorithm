package com.scuyjzh.tree;

import java.util.*;

/**
 * 给你一棵二叉搜索树，请你返回一棵平衡后的二叉搜索树，新生成的树应该与原来的树有着相同的结点值。
 *
 * @author scuyjzh
 * @date 2020/9/21 9:21
 */
class BalanceBinarySearchTree {
    public TreeNode balanceBST(TreeNode root) {
        List<Integer> sortList = new LinkedList<>();
        // 中序遍历构造有序链表
        inOrder(root, sortList);
        // 有序链表构造平衡二叉树
        return buildTree(sortList, 0, sortList.size() - 1);
    }

    private void inOrder(TreeNode root, List<Integer> sortList) {
        if (root != null) {
            inOrder(root.left, sortList);
            sortList.add(root.val);
            inOrder(root.right, sortList);
        }
    }

    private TreeNode buildTree(List<Integer> sortList, int start, int end) {
        if (start > end) {
            return null;
        }
        // 中间结点为root
        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(sortList.get(mid));
        // 递归构造左右子树
        root.left = buildTree(sortList, start, mid - 1);
        root.right = buildTree(sortList, mid + 1, end);
        // 返回root
        return root;
    }

    public static void main(String[] args) {
        BalanceBinarySearchTree solution = new BalanceBinarySearchTree();
        TreeNode root = TreeNode.initBinaryTree("[50,40,80,30,45,60,90,10,35,49,70]");
        System.out.println(solution.balanceBST(root));
    }
}
