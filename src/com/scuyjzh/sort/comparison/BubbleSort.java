package com.scuyjzh.sort.comparison;

import java.util.Arrays;

/**
 * 冒泡排序的基本思想是：对相邻的元素进行两两比较，顺序相反则进行交换；这样，每一趟会将最小或最大的元素“浮”到顶端，最终达到完全有序；
 * 冒泡排序是稳定排序。
 *
 * @author scuyjzh
 * @date 2020/6/29 11:07
 */
class BubbleSort {
    public void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length - 1; ++i) {
            // 设定一个标记，若为true，则表示此次循环没有进行交换，也就是待排序列已经有序，排序已完成
            boolean flag = true;
            for (int j = 0; j < arr.length - i - 1; ++j) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    flag = false;
                }
            }
            if (flag) {
                break;
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
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
