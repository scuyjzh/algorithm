package com.scuyjzh.binarytree;

import java.util.*;

/**
 * @author scuyjzh
 * @date 2020/8/14 2:57
 */
class LeafNumOnKthLevel {
    /**
     * Approach #1 (Iteration by Level Order Traversal - BFS)
     */
    public int getLeafNumOnKthLevel1(TreeNode root, int k) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int curLevel = 0;
        int curLevelNodeTotal = 0;
        while (!queue.isEmpty()) {
            curLevel++;
            curLevelNodeTotal = queue.size();
            // 如果当前层数等于给定层数，则退出
            if (curLevel == k) {
                break;
            }
            // 将下一层节点入队
            int cntNode = 0;
            while (cntNode < curLevelNodeTotal) {
                cntNode++;
                TreeNode cur = queue.remove();
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
        }
        int cntNode = 0;
        int leafNode = 0;
        while (cntNode < curLevelNodeTotal) {
            cntNode++;
            TreeNode cur = queue.remove();
            if (cur.left == null && cur.right == null) {
                leafNode++;
            }
        }
        return leafNode;
    }

    /**
     * Approach #2 (Recursion - DFS)
     */
    public int getLeafNumOnKthLevel2(TreeNode root, int k) {
        return dfs(root, k);
    }

    private int dfs(TreeNode root, int k) {
        if (root == null || k < 1) {
            return 0;
        }
        if (k == 1) {
            if (root.left == null && root.right == null) {
                return 1;
            } else {
                return 0;
            }
        }
        return dfs(root.left, k - 1) + dfs(root.right, k - 1);
    }

    public static void main(String[] args) {
        LeafNumOnKthLevel solution = new LeafNumOnKthLevel();
        TreeNode root = TreeNode.initBinaryTree("[3,9,20,null,null,15,7]");
        System.out.println(solution.getLeafNumOnKthLevel1(root, 3));
        System.out.println(solution.getLeafNumOnKthLevel2(root, 3));
    }
}
