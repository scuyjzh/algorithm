package com.scuyjzh.sort.linear;

import java.util.*;

/**
 * 桶排序的思想是：
 * • 将区间划分为 n 个相同大小的子区间，每个子区间称为一个桶
 * • 遍历数组，将每个数字装入桶中
 * • 对每个桶内的数字单独排序，这里需要采用其他排序算法，如插入、归并、快排等
 * • 最后按照顺序将所有桶内的数字合并起来
 * <p>
 * 桶排序在实际工作中的应用较少，不仅因为它需要借助于其他排序算法，还因为桶排序算法基于一个假设：
 * 所有输入数据都服从均匀分布，也就是说输入数据应该尽可能地均匀分布在每个桶中。
 * 只有这个假设成立时，桶排序运行效率才比较高。
 * 在最差的情况下，所有数据都会被装入同一个桶中，此时桶排序算法只会徒增一轮遍历。
 */
class BucketSort {
    /**
     * 以数组作为桶
     */
    public void bucketSort1(int[] arr) {
        // 判空及防止数组越界
        if (arr == null || arr.length <= 1) {
            return;
        }
        // 找到最大值，最小值
        int max = arr[0];
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            } else if (arr[i] < min) {
                min = arr[i];
            }
        }
        // 确定取值范围
        int range = max - min;
        // 设置桶的数量，这里设置为 100 个，可以根据实际情况修改。
        int bucketAmount = 100;
        // 桶和桶之间的间距
        double gap = range * 1.0 / (bucketAmount - 1);
        // 用二维数组来装桶，第一个维度是桶的编号，第二个维度是桶中的数字。初始化长度为 0
        int[][] buckets = new int[bucketAmount][];
        // 装桶
        for (int value : arr) {
            // 找到 value 属于哪个桶
            int index = (int) ((value - min) / gap);
            buckets[index] = add(buckets[index], value);
        }
        int index = 0;
        // 对每个桶内的数字进行单独排序
        for (int i = 0; i < bucketAmount; i++) {
            if (buckets[i] == null || buckets[i].length == 0) {
                continue;
            }
            // 这里需要结合其他排序算法，例如：插入排序
            insertSort(buckets[i]);
            // 排序完成后将桶内的结果收集起来
            System.arraycopy(buckets[i], 0, arr, index, buckets[i].length);
            index += buckets[i].length;
        }
    }

    /**
     * 数组扩容
     */
    private int[] add(int[] arr, int num) {
        if (arr == null) {
            return new int[]{num};
        }
        int[] newArr = Arrays.copyOf(arr, arr.length + 1);
        newArr[arr.length] = num;
        return newArr;
    }

    /**
     * 插入排序
     */
    private void insertSort(int[] arr) {
        // 从第二个数开始，往前插入数字
        for (int i = 1; i < arr.length; i++) {
            int currentNumber = arr[i];
            int j = i - 1;
            // 寻找插入位置的过程中，不断地将比 currentNumber 大的数字向后挪
            while (j >= 0 && currentNumber < arr[j]) {
                arr[j + 1] = arr[j];
                j--;
            }
            // 两种情况会跳出循环：
            // 1. 遇到一个小于或等于 currentNumber 的数字，跳出循环，currentNumber 就坐到它后面。
            // 2. 已经走到数列头部，仍然没有遇到小于或等于 currentNumber 的数字，也会跳出循环，此时 j 等于 -1，currentNumber 就坐到数列头部。
            arr[j + 1] = currentNumber;
        }
    }

    public void bucketSort2(int[] arr) {
        // 判空及防止数组越界
        if (arr == null || arr.length <= 1) {
            return;
        }
        // 找到最大值，最小值
        int max = arr[0];
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            } else if (arr[i] < min) {
                min = arr[i];
            }
        }
        // 确定取值范围
        int range = max - min;
        // 设置桶的数量，这里设置为 100 个，可以任意修改。
        int bucketAmount = 100;
        // 桶和桶之间的间距
        double gap = range * 1.0 / (bucketAmount - 1);
        HashMap<Integer, LinkedList<Integer>> buckets = new HashMap<>(bucketAmount);
        // 装桶
        for (int value : arr) {
            // 找到 value 属于哪个桶
            int index = (int) ((value - min) / gap);
            if (!buckets.containsKey(index)) {
                buckets.put(index, new LinkedList<>());
            }
            buckets.get(index).add(value);
        }
        int index = 0;
        // 对每个桶内的数字进行单独排序
        for (int i = 0; i < bucketAmount; i++) {
            LinkedList<Integer> bucket = buckets.get(i);
            if (bucket == null) {
                continue;
            }
            // 这里需要结合其他排序算法，例如：插入排序
            insertSort(bucket);
            // 排序完成后将桶内的结果收集起来
            for (int num : bucket) {
                arr[index++] = num;
            }
        }
    }

    /**
     * 对链表插入排序
     */
    private void insertSort(LinkedList<Integer> arr) {
        // 从第二个数开始，往前插入数字
        for (int i = 1; i < arr.size(); i++) {
            int currentNumber = arr.get(i);
            int j = i - 1;
            // 寻找插入位置的过程中，不断地将比 currentNumber 大的数字向后挪
            while (j >= 0 && currentNumber < arr.get(j)) {
                arr.set(j + 1, arr.get(j));
                j--;
            }
            // 两种情况会跳出循环：
            // 1. 遇到一个小于或等于 currentNumber 的数字，跳出循环，currentNumber 就坐到它后面。
            // 2. 已经走到数列头部，仍然没有遇到小于或等于 currentNumber 的数字，也会跳出循环，此时 j 等于 -1，currentNumber 就坐到数列头部。
            arr.set(j + 1, currentNumber);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1201, 292, 121, 72, 233, 44, 12};
        new BucketSort().bucketSort2(arr);
        System.out.println(Arrays.toString(arr));
    }
}
