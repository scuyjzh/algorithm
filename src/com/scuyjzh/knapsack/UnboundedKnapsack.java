package com.scuyjzh.knapsack;

/**
 * Unbounded Knapsack Problem
 *
 * Given a knapsack weight W and a set of n items with certain value val_i and weight wt_i, we need
 * to calculate the maximum amount that could make up this quantity exactly. This is different
 * from classical Knapsack problem, here we are allowed to use unlimited number of instances of
 * an item.
 */
class UnboundedKnapsack {
    public int knapsack1(int n, int W, int[] wt, int[] val) {
        // 常规解法
        int[][] dp = new int[n][W + 1];

        // 先预处理第一件物品
        for (int j = 0; j <= W; ++j) {
            // 显然当只有一件物品的时候，在容量允许的情况下，能选多少件就选多少件
            int maxK = j / wt[0];
            dp[0][j] = maxK * val[0];
        }

        // 处理剩余物品
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j <= W; ++j) {
                // 不考虑第 i 件物品的情况（选择 0 件物品 i）
                int no = dp[i - 1][j];
                // 考虑第 i 件物品的情况
                int yes = 0;
                int maxK = j / wt[i];
                for (int k = 1; k <= maxK; ++k) {
                    yes = Math.max(yes, dp[i - 1][j - k * wt[i]] + k * val[i]);
                }
                dp[i][j] = Math.max(no, yes);
            }
        }
        return dp[n - 1][W];
    }

    public int knapsack2(int n, int W, int[] wt, int[] val) {
        // 滚动数组解法
        int[][] dp = new int[2][W + 1];

        // 先预处理第一件物品
        for (int j = 0; j <= W; j++) {
            // 显然当只有一件物品的时候，在容量允许的情况下，能选多少件就选多少件
            int maxK = j / wt[0];
            dp[0][j] = maxK * val[0];
        }

        // 处理剩余物品
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j <= W; ++j) {
                // 不考虑第 i 件物品的情况（选择 0 件物品 i）
                int no = dp[(i - 1) & 1][j];
                // 考虑第 i 件物品的情况
                int yes = 0;
                int maxK = j / wt[i];
                for (int k = 1; k <= maxK; ++k) {
                    yes = Math.max(yes, dp[(i - 1) & 1][j - k * wt[i]] + k * val[i]);
                }
                // 状态转移方程：dp[i][i] = max(dp[i-1][j], dp[i-1][j-wt[i]]+val[i])
                dp[i & 1][j] = Math.max(no, yes);
            }
        }
        return dp[(n - 1) & 1][W];
    }

    public int knapsack3(int n, int W, int[] wt, int[] val) {
        // 「一维空间优化」解法
        int[] dp = new int[W + 1];
        for (int i = 0; i < n; ++i) {
            // 状态转移方程：dp[i][i] = max(dp[i-1][j], dp[i][j-wt[i]]+val[i])
            // 本质上，完全背包依赖的是「上一行正上方的格子」和「本行左边的格子」。
            // 由于计算 dp[i][i] 的时候，依赖 dp[i][j-wt[i]]。
            // 因此在改为「一维空间优化」时，需要确保 dp[j-wt[i]] 存储的是当前行的值，
            // 即确保 dp[j-wt[i]] 已经被更新，所以遍历方向是从小到大
            for (int j = wt[i]; j <= W; ++j) {
                // 不考虑第 i 件物品的情况（选择 0 件物品 i）
                int no = dp[j];
                // 考虑第 i 件物品的情况
                int yes = dp[j - wt[i]] + val[i];
                dp[j] = Math.max(no, yes);
            }
        }
        return dp[W];
    }

    public static void main(String[] args) {
        int[] wt = new int[]{1, 3, 4, 5};
        int[] val = new int[]{10, 40, 50, 70};
        int n = wt.length;
        int W = 8;
        System.out.println(new UnboundedKnapsack().knapsack1(n, W, wt, val));
        System.out.println(new UnboundedKnapsack().knapsack2(n, W, wt, val));
        System.out.println(new UnboundedKnapsack().knapsack3(n, W, wt, val));
    }
}
