package com.scuyjzh.tree;

/**
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 *
 * @author scuyjzh
 * @date 2020/9/9 10:45
 */
public class LowestCommonAncestor {
    private TreeNode ans;

    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return false;
        }
        /*
            思路：
            f(x) 表示 x 节点的子树中是否包含 p 节点或 q 节点，如果包含为 true，否则为 false
            那么符合条件的最近公共祖先 x 一定满足如下条件：
            (f(lson) && f(rson)) || ((x == p || x == q) && (f(lson) || f(rson)))
            其中 lson 和 rson 分别代表 x 节点的左孩子和右孩子
            1.f(lson) && f(rson) 说明左子树和右子树均包含 p 节点或 q 节点，如果左子树包含的是 p 节点，那么右子树只能包含 q 节点，
              反之亦然，因为 p 节点和 q 节点都是不同且唯一的节点，因此如果满足这个判断条件即可说明 x 就是我们要找的最近公共祖先
            2.第二个判断条件考虑了 x 恰好是 p 节点或 q 节点且它的左子树或右子树有一个包含了另一个节点的情况，
              因此如果满足这个判断条件亦可说明 x 就是我们要找的最近公共祖先
        */
        boolean lson = dfs(root.left, p, q);
        boolean rson = dfs(root.right, p, q);
        if ((lson && rson) || ((root.val == p.val || root.val == q.val) && (lson || rson))) {
            ans = root;
        }
        /*
            由于是自底向上从叶子节点开始更新，所以在所有满足条件的公共祖先中一定是深度最大的祖先先被访问到，
            且由于 f(x) 本身的定义很巧妙，在找到最近公共祖先 x 以后，f(x) 按定义被设置为 true，
            即假定了这个子树中只有一个 p 节点或 q 节点，因此其他公共祖先不会再被判断为符合条件
        */
        return lson || rson || (root.val == p.val || root.val == q.val);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        this.dfs(root, p, q);
        return this.ans;
    }

    public static void main(String[] args) {
        LowestCommonAncestor solution = new LowestCommonAncestor();
        TreeNode root = TreeNode.initBinaryTree("[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15]");
        TreeNode p = new TreeNode(9);
        TreeNode q = new TreeNode(11);
        System.out.println(solution.lowestCommonAncestor(root, p, q).val);
    }
}
