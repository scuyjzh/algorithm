package com.scuyjzh.tree;

/**
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。
 *
 * @author scuyjzh
 * @date 2020/9/11 14:50
 */
class DiameterOfBinaryTree {
    private int ans;

    private int depth(TreeNode node) {
        // 访问到空结点了，返回0
        if (node == null) {
            return 0;
        }
        // 左儿子为根的子树的深度
        int left = depth(node.left);
        // 右儿子为根的子树的深度
        int right = depth(node.right);
        // 计算d_node即L+R+1 并更新ans
        ans = Math.max(ans, left + right + 1);
        // 返回该结点为根的子树的深度
        return Math.max(left, right) + 1;
    }

    public int diameterOfBinaryTree(TreeNode root) {
        this.ans = 1;
        this.depth(root);
        // 一条路径的长度为该路径经过的结点数减一
        return this.ans - 1;
    }

    public static void main(String[] args) {
        DiameterOfBinaryTree solution = new DiameterOfBinaryTree();
        TreeNode root = TreeNode.initBinaryTree("[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15]");
        System.out.println(solution.diameterOfBinaryTree(root));
    }
}
