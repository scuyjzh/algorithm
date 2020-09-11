package com.scuyjzh.sort.linear;

import java.util.*;

/**
 * 基数排序是一种非比较型整数排序算法，其原理是将整数按位数切割成不同的数字，然后按每个位数分别比较。
 * 它是这样实现的：将所有待比较数值（正整数）统一为同样的数位长度，数位较短的数前面补零。然后，从最低位开始，依次进行一次排序。这样从最低位排序一直到最高位排序完成以后，数列就变成一个有序序列。
 * 基数排序的方式可以采用LSD（Least significant digital）或MSD（Most significant digital），LSD的排序方式由键值的最右边开始，而MSD则相反，由键值的最左边开始。
 *
 * @author scuyjzh
 * @date 2020/8/7 15:27
 */
class RadixSort {
    public void sort(int[] arr) {
        // 得到数组中最大的数的位数
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int maxLength = String.valueOf(max).length();

		/*
			定义一个二维数组，表示10个桶，每个桶就是一个一维数组
			1.二维数组包好10个一维数组
			2.为了防止放入数据时溢出，规定每个桶的大小为 arr.length
			3.基数排序是使用空间换时间的经典算法
	    */
        int[][] bucket = new int[10][arr.length];
        // 为了记录每个桶中实际上放了多少个数据，定义一个一维数组来记录各个桶的每次放入数据的个数
        int[] bucketElementCounts = new int[10];
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            // 往桶中存数据，第一次是个位，第二次是十位，依次类推
            for (int value : arr) {
                // 取对应位置上的数
                int digitOfElement = value / n % 10;
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = value;
                bucketElementCounts[digitOfElement]++;
            }
            // 按照桶的顺序取数据
            int index = 0;
            for (int k = 0; k < bucketElementCounts.length; k++) {
                // 当桶中有数据
                if (bucketElementCounts[k] != 0) {
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        arr[index++] = bucket[k][l];
                    }
                }
                // 从桶中取完数据后将其数据个数置为0
                bucketElementCounts[k] = 0;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1200, 292, 121, 72, 233, 44, 12};
        RadixSort radixSort = new RadixSort();
        radixSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}