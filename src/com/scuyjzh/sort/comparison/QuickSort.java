package com.scuyjzh.sort.comparison;

import java.util.Arrays;

/**
 * 快速排序使用分治法（Divide and conquer）策略来把一个序列（list）分为较小和较大的2个子序列，然后递归地排序两个子序列。步骤为：
 * 1.挑选基准值：从数列中挑出一个元素，称为“基准”（pivot），
 * 2.分割：重新排序数列，所有比基准值小的元素摆放在基准前面，所有比基准值大的元素摆在基准后面（与基准值相等的数可以到任何一边）。在这个分割结束之后，对基准值的排序就已经完成，
 * 3.递归排序子序列：递归地将小于基准值元素的子序列和大于基准值元素的子序列排序。
 * 递归到最底部的判断条件是数列的大小是零或一，此时该数列显然已经有序。
 * 选取基准值有数种具体方法，此选取方法对排序的时间性能有决定性影响。
 *
 * @author scuyjzh
 * @date 2020/7/8 16:12
 */
class QuickSort {
    public void sort(int[] array) {
        int len;
        if (array == null
                || (len = array.length) == 0
                || len == 1) {
            return;
        }
        quickSort(array, 0, len - 1);
    }

    private void quickSort(int[] array, int left, int right) {
        if (left > right) {
            return;
        }

        // base中存放基准数
        int base = array[left];
        int i = left, j = right;
        while (i != j) {
            // 顺序很重要，先从右边开始往左找，直到找到比base值小的数
            while (array[j] >= base && i < j) {
                j--;
            }
            // 再从左往右边找，直到找到比base值大的数
            while (array[i] <= base && i < j) {
                i++;
            }
            // 上面的循环结束表示找到了位置或者(i>=j)了，交换两个数在数组中的位置
            if (i < j) {
                int tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
            }
        }

        // 将基准数放到中间的位置（基准数归位）
        array[left] = array[i];
        array[i] = base;

        // 递归，继续向基准的左右两边执行和上面同样的操作
        // i的索引处为上面已确定好的基准值的位置，无需再处理
        quickSort(array, left, i - 1);
        quickSort(array, i + 1, right);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1200, 292, 121, 72, 233, 44, 12};
        QuickSort quickSort = new QuickSort();
        quickSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
