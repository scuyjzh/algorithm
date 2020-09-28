package com.scuyjzh.tree;

import java.util.*;

/**
 * 给定一个二叉树，确定它是否是一个完全二叉树。
 *
 * @author scuyjzh
 * @date 2020/9/11 11:12
 */
class CheckCompleteness {
    public boolean isCompleteTree(TreeNode root) {
        if (root == null) {
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean res = true;
        boolean hasNoChild = false;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.remove();
            if (hasNoChild) {
                // 已经出现有空子树的结点，那么后面的必须为叶子结点
                if (cur.left != null || cur.right != null) {
                    res = false;
                    break;
                }
            } else {
                // 如果左子树和右子树都非空，则继续遍历
                if (cur.left != null && cur.right != null) {
                    queue.add(cur.left);
                    queue.add(cur.right);
                }
                // 如果左子树非空但右子树为空，说明已经出现空结点，那么之后必须都为空子树结点
                else if (cur.left != null) {
                    queue.add(cur.left);
                    hasNoChild = true;
                }
                // 如果左子树为空但右子树非空，说明这棵树已经不是完全二叉树
                else if (cur.right != null) {
                    res = false;
                    break;
                }
                // 如果左右子树都为空，那么之后必须也都为空子树结点
                else {
                    hasNoChild = true;
                }
            }

        }
        return res;
    }

    public static void main(String[] args) {
        CheckCompleteness solution = new CheckCompleteness();
        TreeNode root = TreeNode.initBinaryTree("[1,2,3,4,null]");
        System.out.println(solution.isCompleteTree(root));
    }
}
