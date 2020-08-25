package com.scuyjzh.binarytree;

import com.scuyjzh.structure.TreeNode;

import java.util.*;

/**
 * @author scuyjzh
 * @date 2020/8/25 10:37
 */
class InvertBinaryTree {
    /**
     * 方法一：深度优先搜索
     * 时间复杂度：O(n)，其中 n 是树中节点的个数，树中的每个节点都只被访问一次。在反转之前，不论怎样我们至少都得访问每个节点至少一次，因此这个问题无法做地比 O(n) 更好了
     * 空间复杂度：O(n)，本方法使用了递归，在最坏情况下栈内需要存放 O(h) 个方法调用，其中 h 是树的高度。由于 h∈O(n)，可得出空间复杂度为 O(n)
     */
    public TreeNode invertTree1(TreeNode root) {
        return invert(root);
    }

    private TreeNode invert(TreeNode root) {
        // 递归函数的终止条件，节点为空时返回
        if (root == null) {
            return null;
        }
        TreeNode right = invert(root.right);
        TreeNode left = invert(root.left);
        root.left = right;
        root.right = left;
        return root;
    }

    /**
     * 方法二：广度优先搜索
     * 时间复杂度：O(n)，其中 n 是树中节点的个数，树中的每个节点都只被访问/入队一次
     * 空间复杂度：O(n)，即使在最坏的情况下，也就是队列里包含了树中所有的节点。对于一颗完整二叉树来说，叶子节点那一层拥有 n / 2 = O(n) 个节点
     */
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) {
            return null;
        }
        // 将二叉树中的节点逐层放入队列中，再迭代处理队列中的元素
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            // 每次都从队列中拿一个节点，并交换这个节点的左右子树
            TreeNode cur = queue.remove();
            TreeNode tmp = cur.left;
            cur.left = cur.right;
            cur.right = tmp;
            // 如果当前节点的左子树不为空，则放入队列等待后续处理
            if (cur.left != null) {
                queue.add(cur.left);
            }
            // 如果当前节点的右子树不为空，则放入队列等待后续处理
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
        // 返回处理完的根节点
        return root;
    }
}
