package com.scuyjzh.tree;

import java.util.*;

/**
 * 给定一个二叉树，返回它的后序遍历。
 *
 * @author scuyjzh
 * @date 2020/8/19 11:14
 */
class BinaryTreePostorderTraversal {
    /**
     * Approach #1 (Iteration with Stack - DFS)
     */
    public List<Integer> postorderTraversal1(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        /*
            思路：
            1.将二叉树分为“左”（包括一路向左，经过的所有实际左+根）、“右”（包括实际的右）两种结点
            2.使用同样的顺序将“左”结点入栈
            3.在合适的时机转向（转向后，“右”结点即成为“左”结点）、访问结点、或出栈
        */
        TreeNode cur = root;
        TreeNode pre = null;
        while (cur != null || !stack.isEmpty()) {
            // 后序遍历的实际顺序：根->左->右
            // 入栈顺序不变，我们只需要考虑第3点的变化（合适时机转向）
            // 出栈的对象一定都是“左”结点（“右”结点会在转向后称为“左”结点，然后入栈），也就是实际的左或根
            // 实际的左可以当做左右子树都为 null 的根，所以我们只需要分析实际的根
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            // 对于实际的根，需要保证先后访问了左子树、右子树之后，才能访问根
            // 实际的右结点、左结点、根结点都会成为“左”结点入栈
            // 因此只需在出栈前，将该结点视作实际的根结点，并检查其右子树是否不存在或已被访问即可
            cur = stack.peek();
            // 由于访问根结点前，一定先紧挨着访问了其右子树，所以只需增加一个标志位，记录右子树的访问情况
            if (cur.right == null || cur.right == pre) {
                // 如果不存在右子树或右子树已被访问，那么可以访问根结点
                list.add(cur.val);
                // 并出栈，不需要转向
                stack.pop();
                // 记录上一个访问的结点，用于判断“访问根结点之前，右子树是否已访问过”
                pre = cur;
                // 表示不需要转向，继续弹栈
                cur = null;
            } else {
                // 如果右子树存在且还未被访问过，就转向其右子树，使其“右”结点成为“左”结点，等着它先被访问之后，再来访问根结点
                cur = cur.right;
            }
        }
        return list;
    }

    /**
     * Approach #2 (Iteration with Stack)
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        LinkedList<Integer> list = new LinkedList<>();
        if (root == null) {
            return list;
        }
        /*
            思路：
            1.后序遍历的结点访问顺序为：左 → 右 → 中；将这个次序颠倒过来：中 → 右 → 左
            2.前序遍历的结点访问顺序为：中 → 左 → 右
            3.因此，可以将前序遍历代码中的压栈顺序进行调整，并将结果逆序输出即是后序遍历的访问顺序
        */
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            // 将结点插入链表的头部
            list.addFirst(cur.val);
            // 将压栈的顺序调整为先押入左结点，再压入右结点
            // （前序遍历是先压入右结点，再压入左结点）
            if (cur.left != null) {
                stack.push(cur.left);
            }
            if (cur.right != null) {
                stack.push(cur.right);
            }
        }
        return list;
    }

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

    public static void main(String[] args) {
        BinaryTreePostorderTraversal solution = new BinaryTreePostorderTraversal();
        TreeNode root = TreeNode.initBinaryTree("[5,4,8,11,null,13,4,7,2,null,null,null,null,null,1]");
        System.out.println(solution.postorderTraversal1(root));
        System.out.println(solution.postorderTraversal2(root));
        System.out.println(solution.postorderTraversal3(root));
    }
}
