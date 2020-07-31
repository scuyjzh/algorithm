package com.scuyjzh.sort;

/**
 * @author zhangyijie
 * @date 2020/7/8 16:12
 */
class QuickSort {
    public void sort(int[] arr, int left, int right) {
        if (left < right) {
            // 获取枢纽值，并将其放在当前待处理序列末尾
            dealPivot(arr, left, right);
            // 枢纽值被放在序列末尾
            int pivot = right - 1;
            // 左指针
            int i = left;
            // 右指针
            int j = right - 1;
            while (true) {
                while (arr[++i] < arr[pivot]) {
                }
                while (j > left && arr[--j] > arr[pivot]) {
                }
                if (i < j) {
                    swap(arr, i, j);
                } else {
                    break;
                }
            }
            if (i < right) {
                swap(arr, i, right - 1);
            }
            sort(arr, left, i - 1);
            sort(arr, i + 1, right);
        }
    }

    private void dealPivot(int[] arr, int left, int right) {
        int mid = (left + right) / 2;
        if (arr[left] > arr[mid]) {
            swap(arr, left, mid);
        }
        if (arr[left] > arr[right]) {
            swap(arr, left, right);
        }
        if (arr[right] < arr[mid]) {
            swap(arr, right, mid);
        }
        swap(arr, right - 1, mid);
    }

    private void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
