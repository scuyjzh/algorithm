package com.scuyjzh.binarytree;

import java.util.*;

/**
 * @author scuyjzh
 * @date 2020/8/19 11:14
 */
class BinaryTreePostorderTraversal {
    /**
     * Approach #3 (Recursion - DFS)
     */
    public List<Integer> postorderTraversal3(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        traversal(root, list);
        return list;
    }

    private void traversal(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        traversal(root.left, list);
        traversal(root.right, list);
        list.add(root.val);
    }
}
