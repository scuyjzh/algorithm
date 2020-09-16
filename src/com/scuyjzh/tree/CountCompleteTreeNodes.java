package com.scuyjzh.tree;

/**
 * 给出一个完全二叉树，求出该树的节点个数。
 *
 * @author scuyjzh
 * @date 2020/8/26 16:09
 */
class CountCompleteTreeNodes {
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = countLevel(root.left);
        int right = countLevel(root.right);
        // 利用移位计算 2 ^ left 和 2 ^ right
        // 1.left == right，说明左子树一定是满二叉树
        // 2.left != right，说明右子树一定是满二叉树
        if (left == right) {
            return countNodes(root.right) + (1 << left);
        } else {
            return countNodes(root.left) + (1 << right);
        }
    }

    private int countLevel(TreeNode root) {
        int level = 0;
        while (root != null) {
            level++;
            root = root.left;
        }
        return level;
    }

    public static void main(String[] args) {
        CountCompleteTreeNodes solution = new CountCompleteTreeNodes();
        TreeNode root = TreeNode.initBinaryTree("[1,2,3,4,5,6,7,8,9,10,11,12,13]");
        System.out.println(solution.countNodes(root));
    }
}
