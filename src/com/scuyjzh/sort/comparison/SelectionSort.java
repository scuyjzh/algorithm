package com.scuyjzh.sort.comparison;

import java.util.Arrays;

/**
 * 选择排序是一种简单直观的排序算法。它的工作原理如下：
 * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。
 *
 * @author scuyjzh
 * @date 2020/6/30 23:26
 */
class SelectionSort {
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
        SelectionSort selectionSort = new SelectionSort();
        selectionSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
