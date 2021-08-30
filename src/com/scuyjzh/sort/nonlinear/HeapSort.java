package com.scuyjzh.sort.nonlinear;

import java.util.*;

/**
 * 堆排序（Heap sort）是指利用堆这种数据结构所设计的一种排序算法。
 * 堆是一个近似完全二叉树的结构，并同时满足堆的性质：即子节点的键值或索引总是小于（或者大于）它的父节点。
 * <p>
 * 堆排序过程如下：
 * • 用数列构建出一个大顶堆，取出堆顶的数字；
 * • 调整剩余的数字，构建出新的大顶堆，再次取出堆顶的数字；
 * • 循环往复，完成整个排序。
 * <p>
 * 堆排序分为两个阶段：初始化建堆（buildMaxHeap）和重建堆（maxHeapify，直译为大顶堆化）。
 * 根据数学运算可以推导出初始化建堆的时间复杂度为 O(N)，重建堆的时间复杂度为 O(NlogN)，所以堆排序总的时间复杂度为 O(NlogN)。
 * 堆排序的空间复杂度为 O(1)，只需要常数级的临时变量。
 */
class HeapSort {
    public void heapSort(int[] arr) {
        // 构建初始大顶堆
        buildMaxHeap(arr);
        for (int i = arr.length - 1; i > 0; --i) {
            // 将最大值交换到数组最后
            swap(arr, 0, i);
            // 调整剩余数组，使其满足大顶堆
            maxHeapify(arr, 0, i);
        }
    }

    /**
     * 构建初始大顶堆
     */
    private void buildMaxHeap(int[] arr) {
        // 从最后一个非叶子结点开始调整大顶堆，最后一个非叶子结点的下标就是 arr.length / 2 - 1
        for (int i = arr.length / 2 - 1; i >= 0; --i) {
            maxHeapify(arr, i, arr.length);
        }
    }

    /**
     * 调整大顶堆，第三个参数表示剩余未排序的数字的数量，也就是剩余堆的大小
     */
    private void maxHeapify(int[] arr, int i, int heapSize) {
        // 左子结点下标
        int l = 2 * i + 1;
        // 右子结点下标
        int r = l + 1;
        // 记录根结点、左子树结点、右子树结点三者中的最大值下标
        int largest = i;
        // 与左子树结点比较
        if (l < heapSize && arr[l] > arr[largest]) {
            largest = l;
        }
        // 与右子树结点比较
        if (r < heapSize && arr[r] > arr[largest]) {
            largest = r;
        }
        if (largest != i) {
            // 将最大值交换为根结点
            swap(arr, i, largest);
            // 再次调整交换数字后的大顶堆
            maxHeapify(arr, largest, heapSize);
        }
    }

    /**
     * 调整大顶堆的非递归实现
     */
    public void maxHeapifyIterative(int[] arr, int parentIndex, int heapSize) {
        // value 用来记录初始根结点值
        int value = arr[parentIndex];
        // 初始化子树结点下标，定位到左子树结点
        int childIndex = 2 * parentIndex + 1;
        while (childIndex < heapSize) {
            // 若右子树结点值小于左子树，则定位到右子树结点
            if (childIndex + 1 < heapSize && arr[childIndex + 1] > arr[childIndex]) {
                childIndex++;
            }
            // 若根结点值最大，则直接跳出循环
            if (value >= arr[childIndex]) {
                break;
            }
            // 将最大值赋给根结点（单向赋值，无须交换）
            arr[parentIndex] = arr[childIndex];
            // 更新根结点和子结点下标
            parentIndex = childIndex;
            childIndex = 2 * childIndex + 1;
        }
        // 将初始根结点值赋给当前结点
        arr[parentIndex] = value;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr;
        arr = new int[]{8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        new HeapSort().heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
