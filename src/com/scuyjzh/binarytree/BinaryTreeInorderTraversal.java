package com.scuyjzh.binarytree;

import java.util.*;

/**
 * @author scuyjzh
 * @date 2020/8/14 15:19
 */
class BinaryTreeInorderTraversal {
    /**
     * Approach #1 (Iteration with Stack - DFS)
     */
    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.empty()) {
            // 不断往左子树方向走，每走一次就将当前节点保存到栈中
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            // 当前节点为空，说明左边走到头了，从栈中弹出节点并保存
            curr = stack.pop();
            list.add(curr.val);
            // 然后转向右子树节点，继续上面整个过程
            curr = curr.right;
        }
        return list;
    }

    /**
     * Approach #3 (Recursion - DFS)
     */
    public List<Integer> inorderTraversal3(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        traversal(root, list);
        return list;
    }

    private void traversal(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        traversal(root.left, list);
        list.add(root.val);
        traversal(root.right, list);

    }

    public static void main(String[] args) {
        BinaryTreeInorderTraversal solution = new BinaryTreeInorderTraversal();
        TreeNode root = TreeNode.initBinaryTree("[1,2,3,4,5,6,null,null,null,7,8]");
        System.out.println(solution.inorderTraversal1(root));
        System.out.println(solution.inorderTraversal1(root));
        System.out.println(solution.inorderTraversal3(root));
    }
}
