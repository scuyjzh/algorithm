package com.scuyjzh.sort.linear;

import java.util.*;

/**
 * 计数排序是一种稳定的线性时间排序算法。算法的步骤如下：
 * 1.找出待排序的数组中最大和最小的元素
 * 2.统计数组中每个值为i的元素出现的次数，存入数组C的第i项
 * 3.对所有的计数累加（从C中的第一个元素开始，每一项和前一项相加）
 * 4.反向填充目标数组：将每个元素i放在新数组的第C[i]项，每放一个元素就将C[i]减去1
 *
 * @author scuyjzh
 * @version 1.0
 */
class CountingSort {
    public int[] sort(int[] a) {
        int[] b = new int[a.length];
        int max = a[0], min = a[0];
        for (int i : a) {
            if (i > max) {
                max = i;
            }
            if (i < min) {
                min = i;
            }
        }
        // 这里k的大小是要排序的数组中，元素大小的极值差+1
        int k = max - min + 1;
        int[] c = new int[k];
        for (int i = 0; i < a.length; ++i) {
            // 优化过的地方，减小了数组c的大小
            c[a[i] - min] += 1;
        }
        for (int i = 1; i < c.length; ++i) {
            c[i] = c[i] + c[i - 1];
        }
        for (int i = a.length - 1; i >= 0; --i) {
            // 按存取的方式取出c的元素
            b[--c[a[i] - min]] = a[i];
        }
        return b;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1201, 292, 121, 72, 233, 44, 12};
        CountingSort countingSort = new CountingSort();
        arr = countingSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
