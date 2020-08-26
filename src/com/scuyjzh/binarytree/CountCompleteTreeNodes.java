package com.scuyjzh.binarytree;

import com.scuyjzh.structure.TreeNode;

/**
 * @author scuyjzh
 * @date 2020/8/26 16:09
 */
class CountCompleteTreeNodes {
    public int countNodes(TreeNode root) {
        return dfs(root);
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return dfs(root.left) + dfs(root.right) + 1;
        }
    }

    public static void main(String[] args) {
        CountCompleteTreeNodes solution = new CountCompleteTreeNodes();
        TreeNode root = TreeNode.initBinaryTree("[1,2,3,4,null]");
        System.out.println(solution.countNodes(root));
    }
}
