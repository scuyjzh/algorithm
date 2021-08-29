package com.scuyjzh.sort.nonlinear;

import java.util.*;

/**
 * 希尔排序（Shell sort），也称递减增量排序算法，是插入排序的一种更高效的改进版本。希尔排序是非稳定排序算法。
 * 希尔排序按其设计者希尔（Donald Shell）的名字命名，该算法由1959年公布。
 * <p>
 * 希尔排序是基于插入排序的以下两点性质而提出改进方法的：
 * • 插入排序在对几乎已经排好序的数据操作时，效率高，即可以达到线性排序的效率
 * • 但插入排序一般来说是低效的，因为插入排序每次只能将数据移动一位
 * <p>
 * 事实上，希尔排序时间复杂度非常难以分析，它的平均复杂度界于 O(n) 到 O(n^2) 之间，普遍认为它最好的时间复杂度为 O(n^1.3)。
 * 希尔排序的空间复杂度为 O(1)，只需要常数级的临时变量。
 */
class ShellSort {
    public void shellSort1(int[] arr) {
        /*
         * 每一遍排序的间隔在希尔排序中被称之为增量，所有的增量组成的序列称之为增量序列。
         * 增量依次递减，最后一个增量必须为 1，所以希尔排序又被称之为「缩小增量排序」。
         * 要是以专业术语来描述希尔排序，可以分为以下两个步骤：
         *   • 定义增量序列 D_m > D_{m-1} > D_{m-2} > ... > D_1 = 1
         *   • 对每个 D_k 进行 「D_k 间隔排序」
         *
         * 有一条非常重要的性质保证了希尔排序的效率：
         *   • 「D_{k+1} 间隔」 有序的序列，在经过 「D_k 间隔」 排序后，仍然是 「D_{k+1} 间隔」 有序的
         *
         * 增量序列的选择会极大地影响希尔排序的效率。
         * 本例中，采用的增量序列为 D_m = N / 2, D_k = D_{k+1} / 2，
         * 这个序列正是当年希尔发表此算法的论文时选用的序列，所以也被称之为希尔增量序列。
         */
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // 分组
            for (int i = 0; i < gap; ++i) {
                // 插入排序
                for (int j = i + gap; j < arr.length; j += gap) {
                    // currentNumber 站起来，开始找位置
                    int currentNumber = arr[j];
                    int preIndex = j - gap;
                    while (preIndex >= i && currentNumber < arr[preIndex]) {
                        // 向后挪位置
                        arr[preIndex + gap] = arr[preIndex];
                        preIndex -= gap;
                    }
                    // currentNumber 找到了自己的位置，坐下
                    arr[preIndex + gap] = currentNumber;
                }
            }
        }
    }

    public void shellSort2(int[] arr) {
        /*
         * 现在的处理方式是：处理完一组间隔序列后，再回来处理下一组间隔序列，这非常符合人类思维。
         * 但对于计算机来说，它更喜欢从第 gap 个元素开始，按照顺序将每个元素依次向前插入自己所在的组这种方式。
         * 虽然这个过程看起来是在不同的间隔序列中不断跳跃，但站在计算机的角度，它是在访问一段连续数组。
         *
         * 经过优化之后，这段代码看起来就和插入排序非常相似了，区别仅在于希尔排序最外层嵌套了一个缩小增量的 for 循环；
         * 并且插入时不再是相邻数字挪动，而是以增量为步长挪动。
         */
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // 从 gap 开始，按照顺序将每个元素依次向前插入自己所在的组
            for (int i = gap; i < arr.length; ++i) {
                // currentNumber 站起来，开始找位置
                int currentNumber = arr[i];
                // 该组前一个数字的索引
                int preIndex = i - gap;
                while (preIndex >= 0 && currentNumber < arr[preIndex]) {
                    // 向后挪位置
                    arr[preIndex + gap] = arr[preIndex];
                    preIndex -= gap;
                }
                // currentNumber 找到了自己的位置，坐下
                arr[preIndex + gap] = currentNumber;
            }
        }
    }

    public void shellSortByKnuth(int[] arr) {
        /*
         * 上文说到，增量序列的选择会极大地影响希尔排序的效率。
         * 增量序列如果选得不好，希尔排序的效率可能比插入排序效率还要低。
         * 人们发现：增量元素不互质，则小增量可能根本不起作用。
         *
         * 事实上，希尔排序的增量序列如何选择是一个数学界的难题，但它也是希尔排序算法的核心优化点。
         * 数学界有不少的大牛做过这方面的研究。比较著名的有 Hibbard 增量序列、Knuth 增量序列、Sedgewick 增量序列。
         *   • Hibbard 增量序列：D_k = 2^k - 1，也就是 {1, 3, 7, 15, ...}。
         *     数学界猜想它最坏的时间复杂度为 O(n^{3/2})，平均时间复杂度为 O(n^{5/4});
         *   • Knuth 增量序列：D_1 = 1; D_{k+1} = 3 * D_k + 1，也就是 {1, 4, 13, 40, ...}。
         *     数学界猜想它的平均时间复杂度为 O(n^{3/2})；
         *   • Sedgewick 增量序列：{1, 5, 19, 41, 109, ...}，这个序列的元素有的是通过 9 * 4^k - 9 * 2^k + 1 计算出来的，有的是通过 4^k - 3 * 2^k + 1 计算出来的。
         *     数学界猜想它最坏的时间复杂度为 O(n^{4/3})，平均时间复杂度为 O(n^{7/6})。
         * 以 Knuth 增量序列为例，使用 Knuth 序列进行希尔排序的代码如下：
         */
        // 找到当前数组需要用到的 Knuth 序列中的最大值
        int maxKnuthNumber = 1;
        while (maxKnuthNumber <= arr.length / 3) {
            maxKnuthNumber = maxKnuthNumber * 3 + 1;
        }
        // 增量按照 Knuth 序列规则依次递减
        for (int gap = maxKnuthNumber; gap > 0; gap = (gap - 1) / 3) {
            // 从 gap 开始，按照顺序将每个元素依次向前插入自己所在的组
            for (int i = gap; i < arr.length; ++i) {
                // currentNumber 站起来，开始找位置
                int currentNumber = arr[i];
                // 该组前一个数字的索引
                int preIndex = i - gap;
                while (preIndex >= 0 && currentNumber < arr[preIndex]) {
                    // 向后挪位置
                    arr[preIndex + gap] = arr[preIndex];
                    preIndex -= gap;
                }
                // currentNumber 找到了自己的位置，坐下
                arr[preIndex + gap] = currentNumber;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr;
        arr = new int[]{8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        new ShellSort().shellSort1(arr);
        System.out.println(Arrays.toString(arr));

        arr = new int[]{8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        new ShellSort().shellSort2(arr);
        System.out.println(Arrays.toString(arr));

        arr = new int[]{8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        new ShellSort().shellSortByKnuth(arr);
        System.out.println(Arrays.toString(arr));
    }
}
