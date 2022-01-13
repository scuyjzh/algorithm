package com.scuyjzh.knapsack;

/**
 * 0-1 Knapsack Problem
 *
 * Given weights and values of n items, put these items in a knapsack of capacity W to get the
 * maximum total value in the knapsack. In other words, given two integer arrays val[0..n-1] and
 * wt[0..n-1] which represent values and weights associated with n items respectively. Also given
 * an integer W which represents knapsack capacity, find out the maximum value subset of val[]
 * such that sum of the weights of this subset is smaller than or equal to W. You cannot break an
 * item, either pick the complete item or don’t pick it (0-1 property).
 */
class Knapsack {
    /**
     * A Naive recursive solution of 0/1 Knapsack problem.
     */
    public int knapsack(int n, int W, int[] wt, int[] val) {
        // Base Case
        if (n == 0 || W == 0) {
            return 0;
        }

        // If weight of the nth item is more than Knapsack capacity W,
        // then this item cannot be included in the optimal solution.
        if (wt[n - 1] > W) {
            return knapsack(n - 1, W, wt, val);
        }
        // Return the maximum of two cases:
        // (1) nth item not included
        // (2) included
        else {
            return Math.max(knapsack(n - 1, W, wt, val), knapsack(n - 1, W - wt[n - 1], wt, val) + val[n - 1]);
        }
    }

    /**
     * A Dynamic Programming based solution for 0/1 Knapsack problem.
     */
    public int knapsack1(int n, int W, int[] wt, int[] val) {
        // dp[n][W+1] 解法
        // 其中一维代表当前「当前枚举到哪件物品」，另外一维「现在的剩余重量」，数组装的是「最大价值」
        int[][] dp = new int[n][W + 1];
        // 先处理「考虑第一件物品」的情况
        for (int i = 0; i <= W; ++i) {
            dp[0][i] = i >= wt[0] ? val[0] : 0;
        }
        // 再处理「考虑其余物品」的情况
        for (int i = 1; i < n; ++i) {
            // 状态转移方程：dp[i][i] = max(dp[i-1][j], dp[i-1][j-wt[i]]+val[i])
            for (int j = 0; j <= W; ++j) {
                // 不选该物品
                int no = dp[i - 1][j];
                // 选择该物品，前提「剩余重量」大于等于「物品体积」
                int yes = j >= wt[i] ? dp[i - 1][j - wt[i]] + val[i] : 0;
                dp[i][j] = Math.max(no, yes);
            }
        }
        return dp[n - 1][W];
    }

    public int knapsack2(int n, int W, int[] wt, int[] val) {
        // dp[2][W+1] 解法，采用「滚动数组」的方法进行空间优化
        // 由于计算第 i 行格子只需要第 i-1 行中的某些值，因此可以用一个只有两行的数组来存储中间结果
        int[][] dp = new int[2][W + 1];
        // 先处理「考虑第一件物品」的情况
        for (int i = 0; i < W + 1; i++) {
            dp[0][i] = i >= wt[0] ? val[0] : 0;
        }
        // 再处理「考虑其余物品」的情况
        for (int i = 1; i < n; ++i) {
            // 状态转移方程：dp[i][i] = max(dp[i-1][j], dp[i-1][j-wt[i]]+val[i])
            for (int j = 0; j <= W; ++j) {
                // 不选该物品
                int no = dp[(i - 1) & 1][j];
                // 选择该物品，前提「剩余重量」大于等于「物品体积」
                int yes = j >= wt[i] ? dp[(i - 1) & 1][j - wt[i]] + val[i] : 0;
                // 根据当前计算的行号是偶数还是奇数来交替使用第 0 行和第 1 行
                dp[i & 1][j] = Math.max(no, yes);
            }
        }
        return dp[(n - 1) & 1][W];
    }

    public int knapsack3(int n, int W, int[] wt, int[] val) {
        // dp[W+1] 解法，继续进行空间优化，只保留代表「剩余重量」的维度
        // 由于求解第 i 行格子的值时，不仅是只依赖第 i-1 行，还明确只依赖第 i-1 行的第 c 个格子和第 c-v[i] 个格子，
        // 因此可以将两行的二维数组压缩到一行（转换为一维数组）
        int[] dp = new int[W + 1];
        for (int i = 0; i < n; ++i) {
            // 状态转移方程：dp[i][i] = max(dp[i-1][j], dp[i-1][j-wt[i]]+val[i])
            // 本质上，0-1 背包依赖的是「上一行正上方的格子」和「上一行左边的格子」。
            // 由于计算 dp[i][i] 的时候，依赖 dp[i-1][j-wt[i]]。
            // 因此在改为「一维空间优化」时，需要确保 dp[j-wt[i]] 存储的是上一行的值，
            // 即确保 dp[j-wt[i]] 还没有被覆盖，所以遍历方向是从大到小
            for (int j = W; j >= wt[i]; --j) {
                // 不选该物品
                int no = dp[j];
                // 选择该物品
                int yes = dp[j - wt[i]] + val[i];
                dp[j] = Math.max(no, yes);
            }
        }
        return dp[W];
    }

    public static void main(String[] args) {
        int[] wt = new int[]{10, 20, 30};
        int[] val = new int[]{60, 100, 120};
        int n = wt.length;
        int W = 50;
        System.out.println(new Knapsack().knapsack(n, W, wt, val));
        System.out.println(new Knapsack().knapsack1(n, W, wt, val));
        System.out.println(new Knapsack().knapsack2(n, W, wt, val));
        System.out.println(new Knapsack().knapsack3(n, W, wt, val));
    }
}
