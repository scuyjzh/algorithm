package com.scuyjzh.sort.noncomparison;

import java.util.*;

/**
 * @author scuyjzh
 * @date 2020/8/10 15:57
 */
public class CountingSort {
    public int[] sort(int[] a) {
        int b[] = new int[a.length];
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
        int c[] = new int[k];
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
