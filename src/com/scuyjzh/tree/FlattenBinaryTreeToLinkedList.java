package com.scuyjzh.tree;

import java.util.*;

/**
 * 给定一个二叉树，原地将它展开为一个单链表。
 *
 * @author scuyjzh
 * @date 2020/9/25 11:09
 */
class FlattenBinaryTreeToLinkedList {
    /**
     * 方法一：前序遍历
     */
    public void flatten1(TreeNode root) {
        List<TreeNode> list = new LinkedList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        // 对二叉树进行前序遍历，获得各节点被访问到的顺序
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                list.add(cur);
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            cur = cur.right;
        }
        // 更新每个节点的左右子节点的信息，将二叉树展开为单链表
        int size = list.size();
        for (int i = 1; i < size; i++) {
            TreeNode prev = list.get(i - 1), curr = list.get(i);
            prev.left = null;
            prev.right = curr;
        }
    }

    /**
     * 方法二：前序遍历和展开同步进行
     */
    public void flatten2(TreeNode root) {
        if (root == null) {
            return;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        // 维护上一个访问的节点 pre
        TreeNode pre = null;
        /*
            思路：
            对方法一的前序遍历进行修改，在遍历左子树之前就获得左右子节点的信息，并存入栈内，子节点的信息就不会丢失，就可以将前序遍历和展开为单链表同时进行
            修改后的前序遍历的具体做法是，每次从栈内弹出一个节点作为当前访问的节点，获得该节点的子节点，如果子节点不为空，则依次将右子节点和左子节点压入栈内（注意入栈顺序）。
        */
        while (!stack.isEmpty()) {
            // 每次访问一个节点时，令当前访问的节点为 cur
            TreeNode cur = stack.pop();
            // 初始时 pre 为 null，只有在 pre 不为 null 时才能对 pre 的左右子节点进行更新
            if (pre != null) {
                // 将 pre 的左子节点设为 null
                pre.left = null;
                // 将 pre 的右子节点设为 cur
                pre.right = cur;
            }
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
            // 将 cur 赋值给 pre
            pre = cur;
        }
    }

    /**
     * 方法三：寻找前驱节点
     */
    public void flatten3(TreeNode root) {
        TreeNode cur = root;
        /*
            思路：
            前序遍历访问各节点的顺序是根节点、左子树、右子树。如果一个节点的左子节点为空，则该节点不需要进行展开操作。
            如果一个节点的左子节点不为空，则该节点的左子树中的最后一个节点被访问之后，该节点的右子节点被访问。
            该节点的左子树中最后一个被访问的节点是左子树中的最右边的节点，也是该节点的前驱节点。因此，问题转化成寻找当前节点的前驱节点。
         */
        while (cur != null) {
            // 对于当前节点，如果其左子节点不为空
            if (cur.left != null) {
                TreeNode left = cur.left;
                // 在其左子树中找到最右边的节点，作为前驱节点
                TreeNode pre = left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                // 将当前节点的右子节点赋给前驱节点的右子节点
                pre.right = cur.right;
                // 然后将当前节点的左子节点赋给当前节点的右子节点
                cur.right = left;
                // 并将当前节点的左子节点设为空
                cur.left = null;
            }
            // 继续处理链表中的下一个节点
            cur = cur.right;
        }
    }

    public static void main(String[] args) {
        FlattenBinaryTreeToLinkedList solution = new FlattenBinaryTreeToLinkedList();
        TreeNode root = TreeNode.initBinaryTree("[1,2,7,3,4,null,8,null,null,5,6,null,null,9]");
        solution.flatten1(root);
        solution.flatten2(root);
        solution.flatten3(root);
    }
}
