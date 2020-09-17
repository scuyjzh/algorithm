package com.scuyjzh.tree;

/**
 * @author scuyjzh
 * @date 2020/9/17 9:14
 */
class UniqueBinarySearchTrees {
    /**
     * 方法一：动态规划
     */
    public int numTrees1(int n) {
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
}
