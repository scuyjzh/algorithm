package com.scuyjzh.sort.noncomparison;


import java.util.*;

/**
 * @author zhangyijie
 * @date 2020/8/7 15:27
 */
public class RadixSort {
    public void sort(int[] arr, int radix, int d) {
        // 临时数组
        int[] tempArray = new int[arr.length];
        // count用于记录待排序元素的信息,用来表示该位是i的数的个数
        int[] count = new int[radix];

        int rate = 1;
        for (int i = 0; i < d; i++) {
            // 重置count数组，开始统计下一个关键字
            Arrays.fill(count, 0);
            // 将array中的元素完全复制到tempArray数组中
            System.arraycopy(arr, 0, tempArray, 0, arr.length);

            // 计算每个待排序数据的子关键字
            for (int j = 0; j < arr.length; j++) {
                int subKey = (tempArray[j] / rate) % radix;
                count[subKey]++;
            }
            // 统计count数组的前j位（包含j）共有多少个数
            for (int j = 1; j < radix; j++) {
                count[j] = count[j] + count[j - 1];
            }
            // 按子关键字对指定的数据进行排序 ，因为开始是从前往后放，现在从后忘前读取，保证基数排序的稳定性
            for (int m = arr.length - 1; m >= 0; m--) {
                //插 入到第--count[subKey]位，因为数组下标从0开始
                int subKey = (tempArray[m] / rate) % radix;
                arr[--count[subKey]] = tempArray[m];
            }
            // 前进一位
            rate *= radix;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1200, 292, 121, 72, 233, 44, 12};
        RadixSort radixSort = new RadixSort();
        radixSort.sort(arr, 10, 4);
        System.out.println(Arrays.toString(arr));
    }
}