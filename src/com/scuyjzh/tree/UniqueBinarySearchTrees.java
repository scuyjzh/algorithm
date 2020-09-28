package com.scuyjzh.tree;

/**
 * 给定一个整数 n，求以 1 ... n 为结点组成的二叉搜索树有多少种？
 *
 * @author scuyjzh
 * @date 2020/9/17 9:14
 */
class UniqueBinarySearchTrees {
    public int numTrees(int n) {
        /*
            定义两个函数：
            1.G(n): 长度为 n 的序列能构成的不同二叉搜索树的个数。
            2.F(i,n): 以 i 为根、序列长度为 n 的不同二叉搜索树个数 (1≤i≤n)。
            不同的二叉搜索树的总数 G(n)，是对遍历所有 i (1≤i≤n) 的 F(i,n) 之和。
            根为 i 的所有二叉搜索树的集合是左子树集合和右子树集合的笛卡尔积，
            对于笛卡尔积中的每个元素，加上根结点之后形成完整的二叉搜索树，即F(i,n)=G(i−1)⋅G(n−i)。
        */
        int[] G = new int[n + 1];
        G[0] = 1;
        G[1] = 1;

        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j <= i; ++j) {
                G[i] += G[j - 1] * G[i - j];
            }
        }
        return G[n];
    }

    public static void main(String[] args) {
        UniqueBinarySearchTrees solution = new UniqueBinarySearchTrees();
        System.out.println(solution.numTrees(3));
    }
}
