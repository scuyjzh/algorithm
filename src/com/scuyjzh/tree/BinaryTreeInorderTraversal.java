package com.scuyjzh.tree;

import java.util.*;

/**
 * 给定一个二叉树，返回它的中序遍历。
 *
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
        /*
            思路：
            1.将二叉树分为“左”（包括一路向左，经过的所有实际左+根）、“右”（包括实际的右）两种结点
            2.使用同样的顺序将“左”结点入栈
            3.在合适的时机转向（转向后，“右”结点即成为“左”结点）、访问结点、或出栈
        */
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            // 中序遍历的实际顺序：左->根->右
            while (cur != null) {
                // 不断往左子树方向走，每走一次就将当前结点保存到栈中
                stack.push(cur);
                cur = cur.left;
            }
            // 当前结点为空，说明左边已走到头，从栈中弹出结点并输出（出栈后访问）
            cur = stack.pop();
            // 先序与中序的区别只在于对“左”（根+左子树）结点的处理上
            list.add(cur.val);
            // 然后转向“右”结点，使“右”结点变成新的“左”结点，继续上面整个过程
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
            // 1.如果当前结点的左孩子为空，按照中序遍历规则直接输出当前结点，并将其右孩子作为当前结点
            if (cur.left == null) {
                // 输出当前结点
                list.add(cur.val);
                // 将当前结点更新为右孩子
                cur = cur.right;
            }
            // 2.如果当前结点的左孩子不为空，在当前结点的左子树中找到当前结点的前驱结点
            else {
                pre = cur.left;
                while (pre.right != null && pre.right != cur) {
                    // 退出循环的条件是：
                    // (1)pre.right==null，第一次遍历到当前结点，则执行2.a
                    // (2)pre.right==cur，第二次遍历到当前结点，则执行2.b
                    pre = pre.right;
                }
                // 2.a)如果前驱结点的右孩子为空，则将它的右孩子设置为当前结点
                //     同时将当前结点更新为当前结点的左孩子
                if (pre.right == null) {
                    // 找到当前结点的前驱结点
                    pre.right = cur;
                    // 将当前结点更新为左孩子
                    cur = cur.left;
                }
                // 2.b)如果前驱结点的右孩子为当前结点，此时将它的右孩子重新设为空（恢复树的形状）
                //     同时输出当前结点（在这里输出，这是与前序遍历唯一一点不同）
                //     并将当前结点更新为当前结点的右孩子
                if (pre.right == cur) {
                    // 恢复树的形状
                    pre.right = null;
                    // 输出当前结点
                    list.add(cur.val);
                    // 将当前结点更新为右孩子
                    cur = cur.right;
                }
            }
            // 3.重复以上1、2直到当前结点为空
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
        TreeNode root = TreeNode.initBinaryTree("[6,2,7,1,4,null,9,null,null,3,5,null,null,8]");
        System.out.println(solution.inorderTraversal1(root));
        System.out.println(solution.inorderTraversal2(root));
        System.out.println(solution.inorderTraversal3(root));
    }
}
