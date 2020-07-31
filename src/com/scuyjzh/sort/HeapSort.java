package com.scuyjzh.sort;

import java.util.Arrays;

/**
 * 堆排序的基本思想是：将待排序序列构造成一个大顶堆，此时，整个序列的最大值就是堆顶的根节点。将其与末尾元素进行交换，此时末尾就为最大值。然后将剩余n-1个元素重新构造成一个堆，这样会得到n个元素的次小值。如此反复执行，便能得到一个有序序列。
 * 堆排序是不稳定排序。
 *
 * @author zhangyijie
 * @date 2020/7/3 17:54
 */
class HeapSort {
    public void sort(int[] arr) {
        // 1.构建大顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            // 从第一个非叶子结点从下至上，从右至左调整结构
            adjustHeap(arr, i, arr.length);
        }
        // 2.调整堆结构，交换堆顶元素与末尾元素
        for (int j = arr.length - 1; j > 0; j--) {
            // 将堆顶元素与末尾元素进行交换
            swap(arr, 0, j);
            // 重新对堆进行调整
            adjustHeap(arr, 0, j);
        }
    }

    public void adjustHeap(int[] arr, int parent, int length) {
        int temp = arr[parent];
        int lChild = 2 * parent + 1;
        while (lChild < length) {
            int rChild = lChild + 1;
            if (rChild < length && arr[lChild] < arr[rChild]) {
                lChild++;
            }
            if (arr[lChild] > temp) {
                // 如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
                arr[parent] = arr[lChild];
                parent = lChild;
            } else {
                break;
            }
            lChild = 2 * lChild + 1;
        }
        arr[parent] = temp;
    }

    public void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        HeapSort heapSort = new HeapSort();
        heapSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
