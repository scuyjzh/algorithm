package com.scuyjzh.sort.nonlinear;

import java.util.*;

/**
 * 选择排序（Selection sort）是一种简单直观的排序算法。
 * 选择排序的思想是：双重循环遍历数组，每经过一轮比较，找到最小元素的下标，将其交换至首位。
 * <p>
 * 选择排序使用两层循环，时间复杂度为 O(n^2); 只使用有限个变量，空间复杂度 O(1)。
 */
class SelectionSort {
    public void selectionSort1(int[] arr) {
        /*
         * 选择排序就好比第一个数字站在擂台上，大吼一声：“还有谁比我小？”。
         * 剩余数字来挨个打擂，如果出现比第一个数字小的数，则新的擂主产生。
         * 每轮打擂结束都会找出一个最小的数，将其交换至首位。
         * 经过 n-1 轮打擂，所有的数字就按照从小到大排序完成了。
         *
         * 每一轮排序都找到了当前的最小值，这个最小值就是被选中的数字，将其交换至本轮首位。
         * 这就是「选择排序法」名称的由来。
         */
        int minIndex;
        for (int i = 0; i < arr.length - 1; ++i) {
            minIndex = i;
            for (int j = i + 1; j < arr.length; ++j) {
                if (arr[minIndex] > arr[j]) {
                    // 记录最小值的下标
                    minIndex = j;
                }
            }
            // 将最小元素交换至首位
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;

            // 调试语句
            System.out.println("round " + (i + 1) + ": " + Arrays.toString(arr));
        }
    }

    /**
     * 二元选择排序
     */
    public void selectionSort2(int[] arr) {
        /*
         * 选择排序算法也是可以优化的，既然每轮遍历时找出了最小值，何不把最大值也顺便找出来呢？这就是二元选择排序的思想。
         * 使用二元选择排序，每轮选择时记录最小值和最大值，可以把数组需要遍历的范围缩小一倍。
         * 使用 minIndex 记录最小值的下标，maxIndex 记录最大值的下标。每次遍历后，将最小值交换到首位，最大值交换到末尾，就完成了排序。
         * 由于每一轮遍历可以排好两个数字，所以最外层的遍历只需遍历一半即可。
         *
         * 二元选择排序中有一句很重要的代码，它位于交换最小值和交换最大值的代码中间：
         * if (maxIndex == i) maxIndex = minIndex;
         * 这行代码的作用处理了一种特殊情况：
         * 如果最大值的下标等于 i，也就是说 arr[i] 就是最大值，由于 arr[i] 是当前遍历轮次的首位，
         * 它已经和 arr[minIndex] 交换了，所以最大值的下标需要跟踪到 arr[i] 最新的下标 minIndex。
         */
        int minIndex, maxIndex;
        // i 只需要遍历一半
        for (int i = 0; i < arr.length / 2; i++) {
            minIndex = i;
            maxIndex = i;
            for (int j = i + 1; j < arr.length - i; j++) {
                if (arr[minIndex] > arr[j]) {
                    // 记录最小值的下标
                    minIndex = j;
                }
                if (arr[maxIndex] < arr[j]) {
                    // 记录最大值的下标
                    maxIndex = j;
                }
            }
            // 如果 minIndex 和 maxIndex 都相等，那么他们必定都等于 i，且后面的所有数字都与 arr[i] 相等，此时已经排序完成
            if (minIndex == maxIndex) {
                break;
            }
            // 将最小元素交换至首位
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
            // 如果最大值的下标刚好是 i，由于 arr[i] 和 arr[minIndex] 已经交换了，所以这里要更新 maxIndex 的值。
            if (maxIndex == i) {
                maxIndex = minIndex;
            }
            // 将最大元素交换至末尾
            int lastIndex = arr.length - 1 - i;
            temp = arr[lastIndex];
            arr[lastIndex] = arr[maxIndex];
            arr[maxIndex] = temp;

            // 调试语句
            System.out.println("round " + (i + 1) + ": " + Arrays.toString(arr));
        }
    }

    public static void main(String[] args) {
        int[] arr;
        arr = new int[]{8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        new SelectionSort().selectionSort1(arr);
        System.out.println("sorted: " + Arrays.toString(arr));

        arr = new int[]{8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        new SelectionSort().selectionSort2(arr);
        System.out.println("sorted: " + Arrays.toString(arr));
    }
}
