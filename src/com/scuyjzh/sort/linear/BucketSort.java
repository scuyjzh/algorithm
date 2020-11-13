package com.scuyjzh.sort.linear;

import java.util.*;

/**
 * 桶排序或所谓的箱排序，是一个排序演算法，工作的原理是将阵列分到有限数量的桶里。每个桶再个别排序（有可能再使用别的排序演算法或是以递回方式继续使用桶排序进行排序）。桶排序以下列程序进行：
 * 1.设置一个定量的阵列当作空桶子。
 * 2.寻访序列，并且把项目一个一个放到对应的桶子去。
 * 3.对每个不是空的桶子进行排序。
 * 4.从不是空的桶子里把项目再放回原来的序列中。
 *
 * @author scuyjzh
 * @version 1.0
 */
class BucketSort {
    public void sort(int[] arr) {
        int max = arr[0], min = arr[0];
        for (int a : arr) {
            if (max < a) {
                max = a;
            }
            if (min > a) {
                min = a;
            }
        }
        // 该值也可根据实际情况选择
        int bucketNum = (max - min) / arr.length + 1;
        ArrayList<ArrayList<Integer>> bucketList = new ArrayList<>();
        // create bucket
        for (int i = 0; i < bucketNum; i++) {
            bucketList.add(new ArrayList<>());
        }
        // push into the bucket
        for (int i = 0; i < arr.length; i++) {
            int index = (arr[i] - min) / (arr.length);
            bucketList.get(index).add(arr[i]);
        }
        ArrayList<Integer> bucket;
        int index = 0;
        for (int i = 0; i < bucketNum; i++) {
            bucket = bucketList.get(i);
            insertionSort(bucket);
            for (int k : bucket) {
                arr[index++] = k;
            }
        }

    }

    private void insertionSort(List<Integer> bucket) {
        for (int i = 1; i < bucket.size(); i++) {
            int temp = bucket.get(i);
            int j = i - 1;
            for (; j >= 0 && bucket.get(j) > temp; j--) {
                bucket.set(j + 1, bucket.get(j));
            }
            bucket.set(j + 1, temp);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1201, 292, 121, 72, 233, 44, 12};
        BucketSort bucketSort = new BucketSort();
        bucketSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
