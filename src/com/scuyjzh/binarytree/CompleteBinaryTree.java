package com.scuyjzh.binarytree;

import com.scuyjzh.structure.TreeNode;

import java.util.*;

/**
 * @author scuyjzh
 * @date 2020/8/26 9:50
 */
class CompleteBinaryTree {
    public boolean isComplete(TreeNode root) {
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
                // 已经出现有空子树的节点，那么后面的必须为叶子节点
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
                // 如果左子树非空但右子树为空，说明已经出现空节点，那么之后必须都为空子树节点
                else if (cur.left != null) {
                    queue.add(cur.left);
                    hasNoChild = true;
                }
                // 如果左子树为空但右子树非空，说明这棵树已经不是完全二叉树
                else if (cur.right != null) {
                    res = false;
                    break;
                }
                // 如果左右子树都为空，那么之后必须也都为空子树节点
                else {
                    hasNoChild = true;
                }
            }

        }
        return res;
    }

    public static void main(String[] args) {
        CompleteBinaryTree solution = new CompleteBinaryTree();
        TreeNode root = TreeNode.initBinaryTree("[1,2,3,4,null]");
        System.out.println(solution.isComplete(root));
    }
}
