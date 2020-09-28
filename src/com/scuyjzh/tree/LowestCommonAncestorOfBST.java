package com.scuyjzh.tree;

import java.util.*;

/**
 * 给定一个二叉搜索树, 找到该树中两个指定结点的最近公共祖先。
 *
 * @author scuyjzh
 * @date 2020/9/27 9:46
 */
class LowestCommonAncestorOfBST {
    /**
     * 方法一：两次遍历
     */
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        // 得到从根结点到被寻找结点 p 的路径
        List<TreeNode> path_p = getPath(root, p);
        // 得到从根结点到被寻找结点 q 的路径
        List<TreeNode> path_q = getPath(root, q);
        TreeNode ancestor = null;
        // p 和 q 的最近公共祖先就是从根结点到它们路径上的「分岔点」，也就是最后一个相同的结点
        for (int i = 0; i < path_p.size() && i < path_q.size(); ++i) {
            // 找出最大的编号 i 满足 path_p[i] == path_q[i]
            if (path_p.get(i) == path_q.get(i)) {
                ancestor = path_p.get(i);
            } else {
                break;
            }
        }
        return ancestor;
    }

    private List<TreeNode> getPath(TreeNode root, TreeNode target) {
        List<TreeNode> path = new LinkedList<>();
        // 从根结点开始遍历
        TreeNode node = root;
        // 如果当前结点就是 p，那么成功地找到了结点
        while (node.val != target.val) {
            path.add(node);
            if (target.val < node.val) {
                // 如果当前结点的值大于 p 的值，说明 p 应该在当前结点的左子树，因此将当前结点移动到它的左子结点
                node = node.left;
            } else {
                // 如果当前结点的值小于 p 的值，说明 p 应该在当前结点的右子树，因此将当前结点移动到它的右子结点
                node = node.right;
            }
        }
        path.add(node);
        return path;
    }

    /**
     * 方法二：一次遍历
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        // 从根结点开始遍历
        TreeNode ancestor = root;
        while (true) {
            if (p.val < ancestor.val && q.val < ancestor.val) {
                // 如果当前结点的值大于 p 和 q 的值，说明 p 和 q 应该在当前结点的左子树，因此将当前结点移动到它的左子结点
                ancestor = ancestor.left;
            } else if (p.val > ancestor.val && q.val > ancestor.val) {
                // 如果当前结点的值小于 p 和 q 的值，说明 p 和 q 应该在当前结点的右子树，因此将当前结点移动到它的右子结点
                ancestor = ancestor.right;
            } else {
                // 如果当前结点的值不满足上述两条要求，那么说明当前结点就是「分岔点」
                // 此时，p 和 q 要么在当前结点的不同的子树中，要么其中一个就是当前结点
                break;
            }
        }
        return ancestor;
    }

    /**
     * 方法三：递归法
     */
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        return helper(root, p, q);
    }

    private TreeNode helper(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val < p.val && root.val < q.val) {
            return helper(root.right, p, q);
        }
        if (root.val > p.val && root.val > q.val) {
            return helper(root.left, p, q);
        }
        return root;
    }

    public static void main(String[] args) {
        LowestCommonAncestorOfBST solution = new LowestCommonAncestorOfBST();
        TreeNode root = TreeNode.initBinaryTree("[6,2,8,0,4,7,9,null,null,3,5]");
        TreeNode p = new TreeNode(2);
        TreeNode q = new TreeNode(8);
        System.out.println(solution.lowestCommonAncestor1(root, p, q).val);
        System.out.println(solution.lowestCommonAncestor2(root, p, q).val);
        System.out.println(solution.lowestCommonAncestor3(root, p, q).val);
    }
}
