package com.scuyjzh.binarytree;

import com.scuyjzh.structure.TreeNode;

/**
 * @author scuyjzh
 * @date 2020/8/26 16:09
 */
class CountCompleteTreeNodes {
    /**
     * 方法一：线性时间
     * 时间复杂度：O(N)
     * 空间复杂度：O(d)=O(log N)，其中 d 指的是树的的高度，运行过程中堆栈所使用的空间
     */
    public int countNodes1(TreeNode root) {
        return dfs(root);
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return dfs(root.left) + dfs(root.right) + 1;
        }
    }

    /**
     * 方法二：二分搜索
     * 时间复杂度：O(d^2) = O(log^2 N)，其中 d 指的是树的高度
     * 空间复杂度：O(1)
     */
    public int countNodes2(TreeNode root) {
        // if the tree is empty
        if (root == null) {
            return 0;
        }

        int d = computeDepth(root);
        // if the tree contains 1 node
        if (d == 0) {
            return 1;
        }

        // Last level nodes are enumerated from 0 to 2**d - 1 (left -> right).
        // Perform binary search to check how many nodes exist.
        int left = 1, right = (int) Math.pow(2, d) - 1;
        int pivot;
        while (left <= right) {
            pivot = left + (right - left) / 2;
            if (exists(pivot, d, root)) {
                left = pivot + 1;
            } else {
                right = pivot - 1;
            }
        }

        // The tree contains 2**d - 1 nodes on the first (d - 1) levels
        // and left nodes on the last level.
        return (int) Math.pow(2, d) - 1 + left;
    }

    /**
     * Return tree depth in O(d) time.
     */
    private int computeDepth(TreeNode node) {
        int d = 0;
        while (node.left != null) {
            node = node.left;
            ++d;
        }
        return d;
    }

    /**
     * Last level nodes are enumerated from 0 to 2**d - 1 (left -> right).
     * Return True if last level node idx exists.
     * Binary search with O(d) complexity.
     */
    private boolean exists(int idx, int d, TreeNode node) {
        int left = 0, right = (int) Math.pow(2, d) - 1;
        int pivot;
        for (int i = 0; i < d; ++i) {
            pivot = left + (right - left) / 2;
            if (idx <= pivot) {
                node = node.left;
                right = pivot;
            } else {
                node = node.right;
                left = pivot + 1;
            }
        }
        return node != null;
    }

    public static void main(String[] args) {
        CountCompleteTreeNodes solution = new CountCompleteTreeNodes();
        TreeNode root = TreeNode.initBinaryTree("[1,2,3,4,5,6,7,8,9,10,11,12,13]");
        System.out.println(solution.countNodes1(root));
        System.out.println(solution.countNodes2(root));
    }
}
