package com.scuyjzh.sort;

import java.util.Arrays;

/**
 * 希尔排序是把记录按下标的一定增量分组，对每组使用直接插入排序算法排序；随着增量逐渐减少，每组包含的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止。
 * 希尔排序是不稳定排序。
 *
 * @author scuyjzh
 * @data 2020/6/30 23:36
 */
class ShellSort {
    public void sort(int[] arr) {
        for (int gap = arr.length / 2; gap >= 1; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                while (j - gap >= 0 && arr[j] < arr[j - gap]) {
                    swap(arr, j, j - gap);
                    j -= gap;
                }
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
        ShellSort shellSort = new ShellSort();
        shellSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
