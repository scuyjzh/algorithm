package com.scuyjzh.tree;

/**
 * 给定一个二叉树，它的每个结点都存放着一个整数值。找出路径和等于给定数值的路径总数。
 * 路径不需要从根结点开始，也不需要在叶子结点结束，但是路径方向必须是向下的（只能从父结点到子结点）。
 * 二叉树不超过1000个结点，且结点数值范围是 [-1000000,1000000] 的整数。
 *
 * @author scuyjzh
 * @date 2020/9/28 12:20
 */
class PathSumIII {
    public int pathSum(TreeNode root, int sum) {
        // array 数组存储某一次递归时所遍历结点的结果值，p 表示当前结点的位置，0 表示根结点
        return helper(root, sum, new int[1000], 0);
    }

    public int helper(TreeNode root, int sum, int[] array, int p) {
        if (root == null) {
            return 0;
        }
        array[p] = root.val;
        int curSum = 0;
        int n = 0;
        for (int i = p; i >= 0; i--) {
            curSum += array[i];
            if (curSum == sum) {
                n++;
                // 结点存放值可能为0或者负数，因此不能提前break
            }
        }
        int left = helper(root.left, sum, array, p + 1);
        int right = helper(root.right, sum, array, p + 1);
        return n + left + right;
    }
}
