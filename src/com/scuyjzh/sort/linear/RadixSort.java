package com.scuyjzh.sort.linear;

import java.util.*;

/**
 * 基数排序（Radix sort）是一种非比较型整数排序算法，其原理是将整数按位数切割成不同的数字，然后按每个位数分别比较。
 * 基数排序的发明可以追溯到1887年赫尔曼·何乐礼在打孔卡片制表机（Tabulation Machine）上的贡献。
 * 基数排序的方式可以采用LSD（Least significant digital）或MSD（Most significant digital），LSD的排序方式由键值的最右边开始，而MSD则相反，由键值的最左边开始。
 * <p>
 * 无论 LSD 还是 MSD，基数排序时都需要经历 maxDigitLength 轮遍历，每轮遍历的时间复杂度为 O(n+k) ，其中 k 表示每个基数可能的取值范围大小。
 * 如果是对非负整数排序，则 k = 10，如果是对包含负数的数组排序，则 k = 19。
 * 所以基数排序的时间复杂度为 O(d(n+k))(d 表示最长数字的位数，k 表示每个基数可能的取值范围大小)。
 * 使用的空间和计数排序是一样的，空间复杂度为 O(n + k)O(n+k)（k 表示每个基数可能的取值范围大小）。
 * <p>
 * 基数排序非常适合用于整数排序（尤其是非负整数）。
 */
class RadixSort {
    /**
     * LSD 方式的基数排序
     */
    public void radixSort1(int[] arr) {
        if (arr == null) {
            return;
        }
        // 找出最大的数
        int max = 0;
        for (int value : arr) {
            if (value > max) {
                max = value;
            }
        }
        // 计算最大数字的长度
        int maxDigitLength = 0;
        while (max != 0) {
            maxDigitLength++;
            max /= 10;
        }
        // 使用计数排序算法对基数（0~9）进行排序，下标 [0, 9] 对应基数 [0, 9]
        int[] counting = new int[10];
        int[] result = new int[arr.length];
        int dev = 1;
        for (int i = 0; i < maxDigitLength; ++i) {
            // 遍历 arr 中的每个元素
            for (int value : arr) {
                // 计算当前位的基数
                int radix = value / dev % 10;
                // 将每个基数出现的次数统计到计数数组中对应下标的位置
                counting[radix]++;
            }
            // 每个元素在结果数组中的最后一个下标位置 = 前面比自己小的数字的总数 + 自己的数量 - 1。将 counting[0] 先减去 1，后续 counting 直接累加即可
            counting[0]--;
            for (int j = 1; j < counting.length; ++j) {
                // 将 counting 计算成当前数字在结果中的最后一个下标位置。位置 = 前面比自己小的数字的总数 + 自己的数量 - 1
                // 由于 counting[0] 已经减了 1，所以后续的减 1 可以省略
                counting[j] += counting[j - 1];
            }
            // 从后往前遍历数组，通过 counting 中记录的下标位置，将 arr 中的元素放到 result 数组中
            for (int j = arr.length - 1; j >= 0; --j) {
                // 计算当前位的基数
                int radix = arr[j] / dev % 10;
                // counting[radix] 表示此元素在结果数组中的下标
                result[counting[radix]] = arr[j];
                // 更新 counting[radix]，指向此元素的前一个下标
                counting[radix]--;
            }
            // 计数排序完成后，将结果拷贝回 arr 数组
            System.arraycopy(result, 0, arr, 0, arr.length);
            // 调试语句
            System.out.println("round " + (i + 1) + ": " + Arrays.toString(arr));
            // 将计数数组重置为 0
            Arrays.fill(counting, 0);
            dev *= 10;
        }
    }

    /**
     * 对包含负数的数组进行基数排序
     */
    public void radixSort2(int[] arr) {
        /*
         * 如果数组中包含负数，如何进行基数排序呢？
         * 很容易想到一种思路：将数组中的每个元素都加上一个合适的正整数，使其全部变成非负整数，等到排序完成后，再减去之前加的这个数就可以了。
         * 但这种方案有一个缺点：加法运算可能导致数字越界，所以必须单独处理数字越界的情况。
         *
         * 事实上，有一种更好的方案解决负数的基数排序。
         * 那就是在对基数进行计数排序时，申请长度为 19 的计数数组，用来存储 [-9, 9] 这个区间内的所有整数。
         * 在把每一位基数计算出来后，加上 9，就能对应上 counting 数组的下标了。
         * 也就是说，counting 数组的下标 [0, 18] 对应基数 [-9, 9][。
         */
        if (arr == null) {
            return;
        }
        // 找出最长的数
        int max = 0;
        for (int value : arr) {
            if (Math.abs(value) > max) {
                max = Math.abs(value);
            }
        }
        // 计算最长数字的长度
        int maxDigitLength = 0;
        while (max != 0) {
            maxDigitLength++;
            max /= 10;
        }
        // 使用计数排序算法对基数（-9~9）进行排序，下标 [0, 18] 对应基数 [-9, 9]
        int[] counting = new int[19];
        int[] result = new int[arr.length];
        int dev = 1;
        for (int i = 0; i < maxDigitLength; ++i) {
            // 遍历 arr 中的每个元素
            for (int value : arr) {
                // 计算当前位的基数，并调整下标
                int radix = value / dev % 10 + 9;
                // 将每个基数出现的次数统计到计数数组中对应下标的位置
                counting[radix]++;
            }
            // 每个元素在结果数组中的最后一个下标位置 = 前面比自己小的数字的总数 + 自己的数量 - 1。将 counting[0] 先减去 1，后续 counting 直接累加即可
            counting[0]--;
            for (int j = 1; j < counting.length; ++j) {
                // 将 counting 计算成当前数字在结果中的最后一个下标位置。位置 = 前面比自己小的数字的总数 + 自己的数量 - 1
                // 由于 counting[0] 已经减了 1，所以后续的减 1 可以省略
                counting[j] += counting[j - 1];
            }
            // 从后往前遍历数组，通过 counting 中记录的下标位置，将 arr 中的元素放到 result 数组中
            for (int j = arr.length - 1; j >= 0; --j) {
                // 计算当前位的基数，并调整下标
                int radix = arr[j] / dev % 10 + 9;
                // counting[radix] 表示此元素在结果数组中的下标
                result[counting[radix]] = arr[j];
                // 更新 counting[radix]，指向此元素的前一个下标
                counting[radix]--;
            }
            // 计数排序完成后，将结果拷贝回 arr 数组
            System.arraycopy(result, 0, arr, 0, arr.length);
            // 调试语句
            System.out.println("round " + (i + 1) + ": " + Arrays.toString(arr));
            // 将计数数组重置为 0
            Arrays.fill(counting, 0);
            dev *= 10;
        }
    }

    /**
     * MSD 方式的基数排序
     */
    public void radixSort3(int[] arr) {
        /*
         * 使用 MSD 时，下一轮排序只应该发生在当前轮次基数相等的数字之间，对每一位基数进行递归排序的过程中会产生许多临时变量。
         * 相比 LSD，MSD 的基数排序显得较为复杂。因为每次对基数进行排序后，无法将所有的结果一视同仁地进行下一轮排序，否则下一轮排序会破坏本次排序的结果。
         */
        if (arr == null) {
            return;
        }
        // 找到最大值
        int max = 0;
        for (int value : arr) {
            if (Math.abs(value) > max) {
                max = Math.abs(value);
            }
        }
        // 计算最大长度
        int maxDigitLength = 0;
        while (max != 0) {
            maxDigitLength++;
            max /= 10;
        }
        radixSort(arr, 0, arr.length - 1, maxDigitLength);
    }

    /**
     * 对 arr 数组中的 [start, end] 区间进行基数排序
     */
    private void radixSort(int[] arr, int start, int end, int position) {
        if (start == end || position == 0) {
            return;
        }
        // 使用计数排序算法对基数（-9~9）进行排序，下标 [0, 18] 对应基数 [-9, 9]
        int[] counting = new int[19];
        int[] result = new int[end - start + 1];
        int dev = (int) Math.pow(10, position - 1);
        for (int i = start; i <= end; ++i) {
            // MSD, 从最高位开始
            int radix = arr[i] / dev % 10 + 9;
            counting[radix]++;
        }
        for (int j = 1; j < counting.length; ++j) {
            counting[j] += counting[j - 1];
        }
        // 拷贝 counting，用于待会的递归
        int[] countingCopy = new int[counting.length];
        System.arraycopy(counting, 0, countingCopy, 0, counting.length);
        for (int i = end; i >= start; --i) {
            int radix = arr[i] / dev % 10 + 9;
            result[--counting[radix]] = arr[i];
        }
        // 计数排序完成后，将结果拷贝回 arr 数组
        System.arraycopy(result, 0, arr, start, result.length);
        // 对 [start, end] 区间内的每一位基数进行递归排序
        for (int i = 0; i < counting.length; i++) {
            radixSort(arr, i == 0 ? start : start + countingCopy[i - 1], start + countingCopy[i] - 1, position - 1);
        }
    }

    public static void main(String[] args) {
        int[] arr;
        arr = new int[]{1200, 292, 121, 72, 233, 44, 12};
        new RadixSort().radixSort1(arr);
        System.out.println("sorted: " + Arrays.toString(arr));

        arr = new int[]{1200, 292, -121, 72, 233, -44, 12};
        new RadixSort().radixSort2(arr);
        System.out.println("sorted: " + Arrays.toString(arr));

        arr = new int[]{1200, 292, -121, 72, 233, -44, 12};
        new RadixSort().radixSort3(arr);
        System.out.println("sorted: " + Arrays.toString(arr));
    }
}