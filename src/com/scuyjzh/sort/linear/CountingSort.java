package com.scuyjzh.sort.linear;

import java.util.*;

/**
 * 计数排序（Counting sort）是一种稳定的线性时间排序算法。该算法于1954年由 Harold H. Seward 提出。
 * <p>
 * 从计数排序的实现代码中，可以看到，每次遍历都是进行 n 次或者 k 次，所以计数排序的时间复杂度为 O(n+k)，k 表示数据的范围大小。
 * 用到的空间主要是长度为 k 的计数数组和长度为 n 的结果数组，所以空间复杂度也是 O(n+k)。
 * <p>
 * 计数排序只适用于数据范围不大的场景。
 */
class CountingSort {
    /**
     * 倒序遍历的计数排序
     */
    public void countingSort(int[] arr) {
        // 防止数组越界
        if (arr == null || arr.length <= 1) {
            return;
        }
        // 找到最大值，最小值
        int max = arr[0];
        int min = arr[0];
        for (int i = 1; i < arr.length; ++i) {
            if (arr[i] > max) {
                max = arr[i];
            } else if (arr[i] < min) {
                min = arr[i];
            }
        }
        // 确定计数范围
        int range = max - min + 1;
        // 建立长度为 range 的数组，下标 0~range-1 对应数字 min~max
        int[] counting = new int[range];
        // 遍历 arr 中的每个元素
        for (int element : arr) {
            // 将每个整数出现的次数统计到计数数组中对应下标的位置，这里需要将每个元素减去 min，才能映射到 0～range-1 范围内
            counting[element - min]++;
        }
        // 每个元素在结果数组中的最后一个下标位置 = 前面比自己小的数字的总数 + 自己的数量 - 1。将 counting[0] 先减去 1，后续 counting 直接累加即可
        counting[0]--;
        for (int i = 1; i < range; ++i) {
            // 将 counting 计算成当前数字在结果中的最后一个下标位置。位置 = 前面比自己小的数字的总数 + 自己的数量 - 1
            // 由于 counting[0] 已经减了 1，所以后续的减 1 可以省略
            counting[i] += counting[i - 1];
        }
        int[] result = new int[arr.length];
        // 从后往前遍历数组，通过 counting 中记录的下标位置，将 arr 中的元素放到 result 数组中
        for (int i = arr.length - 1; i >= 0; --i) {
            // counting[arr[i] - min] 表示此元素在结果数组中的下标
            result[counting[arr[i] - min]] = arr[i];
            // 更新 counting[arr[i] - min]，指向此元素的前一个下标
            counting[arr[i] - min]--;
        }
        // 将结果赋值回 arr
        System.arraycopy(result, 0, arr, 0, arr.length);
    }

    public static void main(String[] args) {
        int[] arr;
        arr = new int[]{5, 8, 1, 2, 7, 4, 8, 2, 2, 4};
        new CountingSort().countingSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
