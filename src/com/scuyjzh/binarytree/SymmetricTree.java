package com.scuyjzh.binarytree;

import java.util.*;

/**
 * @author scuyjzh
 * @date 2020/8/19 9:11
 */
class SymmetricTree {
    /**
     * Approach #1 (Iteration with Queue - BFS)
     */
    public boolean isSymmetric1(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            return true;
        }
        // 用队列保存节点
        Queue<TreeNode> queue = new LinkedList<>();
        // 将根节点的左右孩子放到队列中
        queue.add(root.left);
        queue.add(root.right);
        while (!queue.isEmpty()) {
            // 从队列中取出两个节点，再比较这两个节点
            TreeNode left = queue.remove();
            TreeNode right = queue.remove();
            // 如果两个节点都为空就继续循环
            if (left == null && right == null) {
                continue;
            }
            // 两者有一个为空或节点值不相等就返回false
            if (left == null || right == null) {
                return false;
            }
            if (left.val != right.val) {
                return false;
            }
            // 将左节点的左孩子和右节点的右孩子放入队列
            queue.add(left.left);
            queue.add(right.right);
            // 将左节点的右孩子和右节点的左孩子放入队列
            queue.add(left.right);
            queue.add(right.left);
        }
        return true;
    }

    /**
     * Approach #2 (Recursion - DFS)
     */
    public boolean isSymmetric2(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 调用递归函数，比较左节点和右节点
        return dfs(root.left, root.right);
    }

    private boolean dfs(TreeNode left, TreeNode right) {
        // 递归的终止条件是：
        // 1.两个节点都为空
        // 2.两个节点中有一个为空
        // 3.两个节点的值不相等
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        // 再递归比较：
        // 1.左节点的左孩子和右节点的右孩子
        // 2.左节点的右孩子和右节点的左孩子
        return dfs(left.left, right.right) && dfs(left.right, right.left);
    }

    public static void main(String[] args) {
        SymmetricTree solution = new SymmetricTree();
        TreeNode root = TreeNode.initBinaryTree("[1,2,2,3,4,4,3,8,7,6,5,5,6,7,8]");
        System.out.println(solution.isSymmetric1(root));
        System.out.println(solution.isSymmetric1(root));
    }
}
