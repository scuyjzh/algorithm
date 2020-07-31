package com.scuyjzh.sort;

import java.util.Arrays;

/**
 * 简单选择排序的基本思想是：每一趟从待排序的数据元素中选择最小（或最大）的一个元素作为首元素，直到所有元素排完为止；
 * 简单选择排序是不稳定排序。
 *
 * @author scuyjzh
 * @data 2020/6/30 23:26
 */
class SelectSort {
    public void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            // 每一趟循环比较时，min用于存放较小元素的数组下标，这样当前批次比较完毕最终存放的就是此趟内最小的元素的下标，避免每次遇到较小元素都要进行交换
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            // 进行交换，如果min发生变化，则进行交换
            if (min != i) {
                swap(arr, min, i);
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
        SelectSort selectSort = new SelectSort();
        selectSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
