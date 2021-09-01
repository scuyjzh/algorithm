package com.scuyjzh.sort.nonlinear;

import java.util.*;

/**
 * 归并排序（Merge sort），是建立在归并操作上的一种有效的排序算法。1945年由约翰·冯·诺伊曼首次提出。
 * 该算法是采用分治法（Divide and Conquer）的一个非常典型的应用，且各层分治递归可以同时进行。
 * <p>
 * 归并排序的复杂度比较容易分析，拆分数组的过程中，会将数组拆分 logN 次，每层执行的比较次数都约等于 N 次，所以时间复杂度是 O(NlogN)。
 * 空间复杂度是 O(N)，主要占用空间的就是在排序前创建的长度为 N 的 result 数组。
 */
class MergeSort {
    public void mergeSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int[] result = mergeSort(arr, 0, arr.length - 1);
        // 将结果拷贝到 arr 数组中
        System.arraycopy(result, 0, arr, 0, result.length);
    }

    /**
     * 对 arr 的 [start, end] 区间归并排序
     */
    private int[] mergeSort(int[] arr, int start, int end) {
        // 只剩下一个数字，停止拆分，返回单个数字组成的数组
        if (start == end) {
            return new int[]{arr[start]};
        }
        int middle = (start + end) / 2;
        // 拆分左边区域
        int[] left = mergeSort(arr, start, middle);
        // 拆分右边区域
        int[] right = mergeSort(arr, middle + 1, end);
        // 合并左右区域
        return merge(left, right);
    }

    /**
     * 将两个有序数组合并为一个有序数组
     */
    private int[] merge(int[] arr1, int[] arr2) {
        /*
         * 分析归并的过程可知，归并排序是一种稳定的排序算法。其中，对算法稳定性非常重要的一行代码是：
         * if (arr[index1] <= arr[index2]) {
         *     result[index1 + index2] = arr[index1++];
         * }
         * 在这里通过 arr[index1] <= arr[index2] 来合并两个有序数组，保证了原数组中，相同的元素相对顺序不会变化，
         * 如果这里的比较条件写成了 arr[index1] < arr[index2]，则归并排序将变得不稳定。
         */
        int[] result = new int[arr1.length + arr2.length];
        int index1 = 0, index2 = 0;
        while (index1 < arr1.length && index2 < arr2.length) {
            if (arr1[index1] <= arr2[index2]) {
                result[index1 + index2] = arr1[index1++];
            } else {
                result[index1 + index2] = arr2[index2++];
            }
        }
        // 将剩余数字补到结果数组之后
        while (index1 < arr1.length) {
            result[index1 + index2] = arr1[index1++];
        }
        while (index2 < arr2.length) {
            result[index1 + index2] = arr2[index2++];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr;
        arr = new int[]{8, 4, 5, 7, 1, 3, 6, 2};
        new MergeSort().mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
