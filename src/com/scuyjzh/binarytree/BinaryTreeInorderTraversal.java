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
        TreeNode cur = root;
        while (cur != null || !stack.empty()) {
            // 不断往左子树方向走，每走一次就将当前节点保存到栈中
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            // 当前节点为空，说明左边走到头了，从栈中弹出节点并输出
            cur = stack.pop();
            list.add(cur.val);
            // 然后转向右子树节点，继续上面整个过程
            cur = cur.right;
        }
        return list;
    }

    /**
     * Approach #2 (Iteration by Morris Traversal)
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        if (root == null) {
            return list;
        }
        TreeNode cur = root, pre;
        while (cur != null) {
            // 左子树为空，输出当前节点，并将其右孩子作为当前节点
            if (cur.left == null) {
                list.add(cur.val);
                cur = cur.right;
            } else {
                // 左子树不为空
                pre = cur.left;
                // 找到前驱节点，即左子树中的最右节点
                while (pre.right != null && pre.right != cur) {
                    pre = pre.right;
                }
                // 如果前驱节点的右孩子为空，则将它的右孩子设置为当前节点
                // 同时将当前节点更新为当前节点的左孩子
                if (pre.right == null) {
                    pre.right = cur;
                    cur = cur.left;
                }
                // 如果前驱节点的右孩子为当前节点，则将它的右孩子重新设为空（恢复树的形状，斩断线索）
                // 同时输出当前节点，并将当前节点更新为当前节点的右孩子
                if (pre.right == cur) {
                    pre.right = null;
                    list.add(cur.val);
                    cur = cur.right;
                }
            }
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
        TreeNode root = TreeNode.initBinaryTree("[1,2,3,4,5,6,null]");
        System.out.println(solution.inorderTraversal1(root));
        System.out.println(solution.inorderTraversal2(root));
        System.out.println(solution.inorderTraversal3(root));
    }
}
