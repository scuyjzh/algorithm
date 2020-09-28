package com.scuyjzh.tree;

import java.util.*;

/**
 * 给定一个二叉树和一个目标和，找到所有从根结点到叶子结点路径总和等于给定目标和的路径。
 *
 * @author scuyjzh
 * @date 2020/9/24 17:51
 */
class PathSumII {
    /**
     * Approach #1 (Iteration by Postorder Traversal)
     */
    public List<List<Integer>> pathSum1(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        List<Integer> path = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        TreeNode pre = null;
        // 记录当前累计的和
        int curSum = 0;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                // 每走一步，就将结点加入到路径中
                path.add(cur.val);
                // 累加当前已走路径的和
                curSum += cur.val;
                cur = cur.left;
            }
            // 在出栈前，将栈顶视作实际的根结点，并检查其右子树是否不存在或已被访问
            cur = stack.peek();
            if (cur.left == null && cur.right == null && curSum == sum) {
                // path 全局只有一份，必须做拷贝
                res.add(new ArrayList<>(path));
            }
            if (cur.right == null || cur.right == pre) {
                // 如果不存在右子树或右子树已被访问，那么可以访问根结点，将其弹出栈
                stack.pop();
                // 减去出栈的值
                curSum -= cur.val;
                // 移除路径中的出栈结点
                path.remove(path.size() - 1);
                // 记录上一个访问的结点，用于判断“访问根结点之前，右子树是否已访问过”
                pre = cur;
                // 表示不需要转向，继续弹栈
                cur = null;
            } else {
                // 如果右子树存在且还未被访问过，就转向其右子树
                cur = cur.right;
            }
        }
        return res;
    }

    /**
     * Approach #2 (Recursion - DFS)
     */
    public List<List<Integer>> pathSum2(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        List<Integer> path = new ArrayList<>();
        dfs(root, sum, path, res);
        return res;
    }

    private void dfs(TreeNode root, int sum, List<Integer> path, List<List<Integer>> res) {
        // 递归终止条件
        if (root == null) {
            return;
        }
        path.add(root.val);
        if (root.left == null && root.right == null && root.val == sum) {
            // path 全局只有一份，必须做拷贝
            res.add(new ArrayList<>(path));
        } else {
            dfs(root.left, sum - root.val, path, res);
            dfs(root.right, sum - root.val, path, res);
        }
        // back-tracking：递归完成以后，必须重置变量
        path.remove(path.size() - 1);
    }

    public static void main(String[] args) {
        PathSumII solution = new PathSumII();
        TreeNode root = TreeNode.initBinaryTree("[5,4,8,11,null,13,4,7,2,null,null,null,null,5,1]");
        System.out.println(solution.pathSum1(root, 22));
        System.out.println(solution.pathSum2(root, 22));
    }
}
