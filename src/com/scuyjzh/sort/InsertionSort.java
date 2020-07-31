package com.scuyjzh.sort;

import java.util.Arrays;

/**
 * 直接插入排序的基本思想是：每一步将一个待排序的记录，插入到前面已经排好序的有序序列中去，直到插完所有元素为止；
 * 直接插入排序是稳定排序。
 *
 * @author scuyjzh
 * @data 2020/6/30 23:12
 */
class InsertionSort {
    public void sort(int[] arr) {
        for (int i = 1; i < arr.length; ++i) {
            int j = i;
            while (j > 0 && arr[j] < arr[j - 1]) {
                swap(arr, j, j - 1);
                j--;
            }
        }
    }

    private void swap(int[] arr, int a, int b) {
        arr[a] = arr[a] + arr[b];
        arr[b] = arr[a] - arr[b];
        arr[a] = arr[a] - arr[b];
    }

    public static void main(String[] args) {
        int[] arr = new int[]{8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        InsertionSort insertionSort = new InsertionSort();
        insertionSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
