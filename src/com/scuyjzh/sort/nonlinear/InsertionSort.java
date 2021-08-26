package com.scuyjzh.sort.nonlinear;

import java.util.*;

/**
 * 插入排序（Insertion Sort）是一种简单直观的排序算法。
 * 它的工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
 * 插入排序在实现上，通常采用in-place排序（即只需用到 O(1) 的额外空间的排序），
 * 因而在从后向前扫描过程中，需要反复把已排序元素逐步向后挪位，为最新元素提供插入空间。
 * <p>
 * 插入排序过程需要两层循环，时间复杂度为 O(n^2)；只需要常量级的临时变量，空间复杂度为 O(1)。
 */
class InsertionSort {
    /**
     * 交换法插入排序
     */
    public void insertSort1(int[] arr) {
        /*
         * 当数字少于两个时，不存在排序问题，当然也不需要插入，所以直接从第二个数字开始往前插入。
         *
         * 整个过程就像是已经有一些数字坐成了一排，这时一个新的数字要加入，
         * 这个新加入的数字原本坐在这一排数字的最后一位，然后它不断地与前面的数字比较，
         * 如果前面的数字比它大，它就和前面的数字交换位置。
         */
        for (int i = 1; i < arr.length; ++i) {
            // j 记录当前数字下标
            int j = i;
            // 当前数字比前一个数字小，则将当前数字与前一个数字交换
            while (j >= 1 && arr[j] < arr[j - 1]) {
                swap(arr, j, j - 1);
                // 更新当前数字下标
                j--;
            }

            // 调试语句
            int[] sorted = new int[i + 1];
            int[] unsorted = new int[arr.length - i - 1];
            System.arraycopy(arr, 0, sorted, 0, i + 1);
            System.arraycopy(arr, i + 1, unsorted, 0, arr.length - i - 1);
            System.out.println("round " + i + ": " + Arrays.toString(sorted) + " " + Arrays.toString(unsorted));
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void insertSort2(int[] arr) {
        /*
         * 在交换法插入排序中，每次交换数字时，swap 函数都会进行三次赋值操作。
         * 但实际上，新插入的这个数字并不一定适合与它交换的数字所在的位置。
         * 也就是说，它刚换到新的位置上不久，下一次比较后，如果又需要交换，它马上又会被换到前一个数字的位置。
         *
         * 由此，可以想到一种优化方案：
         * 让新插入的数字先进行比较，前面比它大的数字不断向后移动，直到找到适合这个新数字的位置后，新数字只做一次插入操作即可。
         * 这种方案需要把新插入的数字暂存起来，代码如下：
         */
        for (int i = 1; i < arr.length; ++i) {
            int currentNumber = arr[i];
            int j = i - 1;
            // 寻找插入位置的过程中，不断地将比 currentNumber 大的数字向后挪
            while (j >= 0 && currentNumber < arr[j]) {
                arr[j + 1] = arr[j];
                j--;
            }
            // 两种情况会跳出循环：
            // 1. 遇到一个小于或等于 currentNumber 的数字，跳出循环，currentNumber 就坐到它后面。
            // 2. 已经走到数列头部，仍然没有遇到小于或等于 currentNumber 的数字，也会跳出循环，此时 j 等于 -1，currentNumber 就坐到数列头部。
            arr[j + 1] = currentNumber;

            // 调试语句
            int[] sorted = new int[i + 1];
            int[] unsorted = new int[arr.length - i - 1];
            System.arraycopy(arr, 0, sorted, 0, i + 1);
            System.arraycopy(arr, i + 1, unsorted, 0, arr.length - i - 1);
            System.out.println("round " + i + ": " + Arrays.toString(sorted) + " " + Arrays.toString(unsorted));
        }
    }

    public static void main(String[] args) {
        int[] arr;
        arr = new int[]{8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        new InsertionSort().insertSort1(arr);
        System.out.println("sorted: " + Arrays.toString(arr));

        arr = new int[]{8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        new InsertionSort().insertSort2(arr);
        System.out.println("sorted: " + Arrays.toString(arr));
    }
}
