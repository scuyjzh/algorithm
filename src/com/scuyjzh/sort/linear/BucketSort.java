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
        /*
         * 以数组作为桶实现的桶排序，它最大的缺点就是每个桶都和待排序数组一样长，非常消耗内存，容易导致「超出内存限制」错误。
         * 可以做一个优化：声明时所有的桶都为空，当需要添加数字时，不断扩容，并加入新数字。
         * 优化之后，以数组作为桶就不会造成太大的内存消耗了，并且不再需要单独采用一个数组来记录每个桶的长度。
         */
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
        // 设置桶的数量，这里设置为 100 个，可以根据实际情况修改
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
        /*
         * 这里的扩容算法和 ArrayList 扩容很相似，先开辟一个更长的新数组，并将原数组拷贝过来，再加入新数字。
         * 但 ArrayList 扩容时，数组长度是先从 0 扩容到 10，后续再不断乘以 1.5 倍，这会造成一定的内存浪费。
         * 无论是数组还是 ArrayList，扩容过程都比较耗时，所以这个优化属于用时间换空间。
         */
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
            // 1. 遇到一个小于或等于 currentNumber 的数字，跳出循环，currentNumber 就坐到它后面；
            // 2. 已经走到数列头部，仍然没有遇到小于或等于 currentNumber 的数字，也会跳出循环，此时 j 等于 -1，currentNumber 就坐到数列头部。
            arr[j + 1] = currentNumber;
        }
    }

    /**
     * 以链表作为桶
     */
    public void bucketSort2(int[] arr) {
        /*
         * 首先，仍然是找到数组中的最大值和最小值，确定出数据的取值范围，然后划分 100 个桶，计算出间距。
         * 并且把所有的数字都放入 LinkedList 链表中。装桶后，再对链表进行插入排序即可。
         *
         * 采用 LinkedList，装桶时不会有额外的空间浪费，但装桶后排序会比较耗时，
         * 因为访问 LinkedList 链表时，get 和 set 方法都需要从链表头部开始，逐个向后寻找结点，效率较低。
         *
         * 使用链表排序还有一个问题：由于链表中不能存储基本类型，所以不得不将链表类型声明为 LinkedList<Integer>，
         * int 和 Integer 互相转换的过程被称为 「装箱」和「拆箱」，这也会造成额外的性能消耗。
         */
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
        // 设置桶的数量，这里设置为 100 个，可以任意修改
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
            // 1. 遇到一个小于或等于 currentNumber 的数字，跳出循环，currentNumber 就坐到它后面；
            // 2. 已经走到数列头部，仍然没有遇到小于或等于 currentNumber 的数字，也会跳出循环，此时 j 等于 -1，currentNumber 就坐到数列头部。
            arr.set(j + 1, currentNumber);
        }
    }

    /**
     * 折中的方案：装桶时用链表，桶内排序用数组
     */
    public void bucketSort3(int[] arr) {
        /*
         * 为了解决以上两种数据结构各自的痛点，可以采用一种折中的方案：
         * 装桶时使用 LinkedList，避免扩容问题，桶内排序时将链表转换为数组，再进行排序，
         * 避免 LinkedList 排序较慢的问题和大量 「装箱」和「拆箱」的性能消耗（整个链表中的 Integer 都只需要拆箱一次）。
         *
         * 在桶内排序前，通过这行代码：
         * int[] arrInBucket = bucket.stream().mapToInt(Integer::intValue).toArray();
         * 将 LinkedList 转换为 int[]，然后再进行插入排序。
         * 代价是这里多了一个中间变量 arrInBucket，它会占用 O(n) 的空间，
         * 并且 LinkedList 转换为 int[] 的过程需要遍历一次数组，这会增加 O(n) 的时间。
         */
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
        // 设置桶的数量，这里设置为 100 个，可以任意修改
        int bucketAmount = 100;
        // 桶和桶之间的间距
        double gap = range * 1.0 / (bucketAmount - 1);
        HashMap<Integer, Queue<Integer>> buckets = new HashMap<>();
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
            Queue<Integer> bucket = buckets.get(i);
            if (bucket == null) {
                continue;
            }
            // 将链表转换为数组，提升排序性能
            int[] arrInBucket = bucket.stream().mapToInt(Integer::intValue).toArray();
            // 这里需要结合其他排序算法，例如：插入排序
            insertSort(arrInBucket);
            // 排序完成后将桶内的结果收集起来
            System.arraycopy(arrInBucket, 0, arr, index, arrInBucket.length);
            index += arrInBucket.length;
        }
    }

    public static void main(String[] args) {
        /*
         * 总结一下桶排序算法中，采用各个数据结构作为桶的特点：
         *   • 以数组作为桶，初始化每个桶的长度为 n：时间上做到了最好，但空间占用很高。
         *   • 以数组作为桶，初始化每个桶的长度为 0：空间上做到了最好，但装桶时对数组扩容比较耗时。
         *   • 以 LinkedList 作为桶：空间上做到了最好，并且装桶时无需扩容，但对 LinkedList 排序比较耗时。
         *   • 装桶时采用 LinkedList，排序时采用数组：时间和空间上都是一种折中的方案，但 LinkedList 转换
         *     int[] 的过程需要遍历一次数组，增加了 O(n) 的时间，转换后会占用 O(n) 的空间。
         */
        int[] arr;
        arr = new int[]{1201, 292, 121, 72, 233, 44, 12};
        new BucketSort().bucketSort1(arr);
        System.out.println(Arrays.toString(arr));

        arr = new int[]{1201, 292, 121, 72, 233, 44, 12};
        new BucketSort().bucketSort2(arr);
        System.out.println(Arrays.toString(arr));

        arr = new int[]{1201, 292, 121, 72, 233, 44, 12};
        new BucketSort().bucketSort3(arr);
        System.out.println(Arrays.toString(arr));
    }
}
