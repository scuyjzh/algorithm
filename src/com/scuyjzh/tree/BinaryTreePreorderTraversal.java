package com.scuyjzh.tree;

import java.util.*;

/**
 * 给定一个二叉树，返回它的前序遍历。
 *
 * @author scuyjzh
 * @date 2020/8/14 11:41
 */
class BinaryTreePreorderTraversal {
    /**
     * Approach #1 (Iteration with Stack - DFS)
     */
    public List<Integer> preorderTraversal1(TreeNode root) {
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
            // 先序遍历的实际顺序：根->左->右
            while (cur != null) {
                // 每次遍历到“左”结点时立即输出（入栈前访问），因为先序先访问实际根，后访问实际左
                list.add(cur.val);
                // 将访问过的“左”结点入栈
                stack.push(cur);
                // 继续向左子树方向走
                cur = cur.left;
            }
            // 此时已访问过所以“左”（根+左子树）结点，只需将这些没用的结点出栈
            cur = stack.pop();
            // 并转向到“右”结点，使“右”结点变成新的“左”结点，后续处理同上
            cur = cur.right;
        }
        return list;
    }

    /**
     * Approach #2 (Iteration with Stack - DFS)
     */
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        if (root == null) {
            return list;
        }
        // 定义栈模拟二叉树，利用其先入后出的特点，结点出栈顺序为根->左->右，进栈顺序则为右->左->根
        Deque<TreeNode> stack = new ArrayDeque<>();
        // 先把根结点压入栈中
        stack.push(root);
        while (!stack.isEmpty()) {
            // 弹出栈的顶层结点，并输出结果
            TreeNode cur = stack.pop();
            list.add(cur.val);
            // 进栈：右 -> 左 -> 根
            // 出栈：根 -> 左 -> 右
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
        return list;
    }

    /**
     * Approach #3 (Iteration by Morris Traversal)
     */
    public List<Integer> preorderTraversal3(TreeNode root) {
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
                //     同时输出当前结点（在这里输出，这是与中序遍历唯一一点不同）
                //     并将当前结点更新为当前结点的左孩子
                if (pre.right == null) {
                    // 找到当前结点的前驱结点
                    pre.right = cur;
                    // 输出当前结点
                    list.add(cur.val);
                    // 将当前结点更新为左孩子
                    cur = cur.left;
                }
                // 2.b)如果前驱结点的右孩子为当前结点，此时将它的右孩子重新设为空（恢复树的形状）
                //     同时将当前结点更新为当前结点的右孩子
                if (pre.right == cur) {
                    // 恢复树的形状
                    pre.right = null;
                    // 将当前结点更新为右孩子
                    cur = cur.right;
                }
            }
            // 3.重复以上1、2直到当前结点为空
        }
        return list;
    }

    /**
     * Approach #4 (Recursion - DFS)
     */
    public List<Integer> preorderTraversal4(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        traversal(root, list);
        return list;
    }

    private void traversal(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        traversal(root.left, list);
        traversal(root.right, list);
    }

    public static void main(String[] args) {
        BinaryTreePreorderTraversal solution = new BinaryTreePreorderTraversal();
        TreeNode root = TreeNode.initBinaryTree("[1,2,7,3,4,null,8,null,null,5,6,null,null,9]");
        System.out.println(solution.preorderTraversal1(root));
        System.out.println(solution.preorderTraversal2(root));
        System.out.println(solution.preorderTraversal3(root));
        System.out.println(solution.preorderTraversal4(root));
    }
}
