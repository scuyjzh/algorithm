package com.scuyjzh.unionfind;

import java.util.Arrays;

class UnionFind {
    int[] root;
    /**
     * 添加了 rank 数组来记录每个顶点的高度，也就是每个顶点的「秩」
     */
    int[] rank;

    public UnionFind(int size) {
        root = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            root[i] = i;
            // 一开始每个顶点的初始「秩」为 1，因为它们只有自己本身的一个顶点
            rank[i] = 1;
        }
    }

    /**
     * 路径压缩优化的 find 函数
     */
    public int find(int x) {
        System.out.println(Arrays.toString(root));
        if (x == root[x]) {
            return x;
        }
        return root[x] = find(root[x]);
    }

    /**
     * 按秩合并优化的 union 函数
     */
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            if (rank[rootX] > rank[rootY]) {
                root[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                root[rootX] = rootY;
            } else {
                root[rootY] = rootX;
                rank[rootX] += 1;
            }
        }
    }

    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }

    public static void main(String[] args) throws Exception {
        UnionFind uf = new UnionFind(10);
        // 1-2-5-6-7 3-8-9 4
        uf.union(1, 2);
        uf.union(2, 5);
        uf.union(5, 6);
        uf.union(6, 7);
        uf.union(3, 8);
        uf.union(8, 9);
        System.out.println(uf.connected(1, 5));
        System.out.println(uf.connected(5, 7));
        System.out.println(uf.connected(4, 9));
        // 1-2-5-6-7 3-8-9-4
        uf.union(9, 4);
        System.out.println(uf.connected(4, 9));
    }
}
