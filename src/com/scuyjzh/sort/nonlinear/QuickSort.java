package com.scuyjzh.sort.nonlinear;

import java.util.*;

/**
 * 快速排序（Quick sort），又称分区交换排序（partition-exchange sort），简称快排，一种排序算法，最早由东尼·霍尔提出。
 * <p>
 * 快速排序算法的基本思想是：
 * • 从数组中取出一个数，称之为基数（pivot）
 * • 遍历数组，将比基数大的数字放到它的右边，比基数小的数字放到它的左边。遍历完成后，数组被分成了左右两个区域
 * • 将左右两个区域视为两个数组，重复前两个步骤，直到排序完成
 * <p>
 * 在平均状况下，排序 n 个项目要 O(NlogN) 次比较。在最坏状况下则需要 O(n^2) 次比较，但这种状况并不常见。
 * 事实上，快速排序 O(NlogN) 通常明显比其他算法更快，因为它的内部循环（inner loop）可以在大部分的架构上很有效率地达成。
 * <p>
 * 从代码实现中可以分析出，快速排序是一种不稳定的排序算法，在分区过程中，相同数字的相对顺序可能会被修改。
 */
class QuickSort {
    public void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(int[] arr, int start, int end) {
        /*
         * 这里的 start >= end 实际上只有两种情况：
         * • start == end: 表明区域内只有一个数字
         * • start == end + 1: 表明区域内一个数字也没有
         * 不会存在 start 比 end 大 2 或者大 3 之类的。
         */
        if (start >= end) {
            return;
        }
        // 将 arr 从 start 到 end 分区，左边区域比基数小，右边区域比基数大，然后返回中间值的下标
        int middle = partition1(arr, start, end);
        // 对左边区域快速排序
        quickSort(arr, start, middle - 1);
        // 对右边区域快速排序
        quickSort(arr, middle + 1, end);
    }

    /**
     * 最简单的分区算法
     */
    private int partition1(int[] arr, int start, int end) {
        // 取第一个数为基数
        int pivot = arr[start];
        // 从第二个数开始分区
        int left = start + 1;
        // 右边界
        int right = end;
        // left、right 相遇时退出循环
        while (left < right) {
            // 找到第一个大于基数的位置
            while (left < right && arr[left] <= pivot) {
                left++;
            }
            // 交换这两个数，使得左边分区都小于或等于基数，右边分区大于或等于基数
            if (left != right) {
                swap(arr, left, right);
                right--;
            }
        }
        /*
         * 因为选择了数组的第一个元素作为基数，并且分完区后，会执行将基数和中间值交换的操作，这就意味着交换后的中间值会被分到左边区域。
         * 所以需要保证中间值的下标是分区完成后，最后一个比基数小的值，这里用 right 来记录这个值。
         *
         * 这段代码有一个细节。
         * 首先，在交换 left 和 right 之前，判断了 left != right，
         * 这是因为如果剩余的数组都比基数小，则 left 会加到 right 才停止，这时不应该发生交换。
         * 因为 right 已经指向了最后一个比基数小的值。
         *
         * 但这里的拦截可能会拦截到一种错误情况，如果剩余的数组只有最后一个数比基数大，left 仍然加到 right 停止了，但并没有发生交换。
         * 所以在退出循环后，单独比较了 arr[right] 和 pivot。
         *
         * 实际上，这行单独比较的代码非常巧妙，一共处理了三种情况：
         *   • 一是刚才提到的剩余数组中只有最后一个数比基数大的情况
         *   • 二是 left 和 right 区间内只有一个值，则初始状态下， left == right，
         *     所以 while (left < right) 根本不会进入，所以此时单独比较这个值和基数的大小关系
         *   • 三是剩余数组中每个数都比基数大，此时 right 会持续减小，直到和 left 相等退出循环，
         *     此时 left 所在位置的值还没有和 pivot 进行比较，所以单独比较 left 所在位置的值和基数的大小关系
         */
        // 如果 left 和 right 相等，单独比较 arr[right] 和 pivot
        if (left == right && arr[right] > pivot) {
            right--;
        }
        // 将基数和中间数交换
        if (right != start) {
            swap(arr, start, right);
        }
        // 返回中间值的下标
        return right;
    }

    /**
     * 双指针分区算法
     */
    public int partition2(int[] arr, int start, int end) {
        /*
         * 除了上述的分区算法外，还有一种双指针的分区算法更为常用：
         * 从 left 开始，遇到比基数大的数，记录其下标；再从 right 往前遍历，找到第一个比基数小的数，记录其下标；
         * 然后交换这两个数。继续遍历，直到 left 和 right 相遇。
         *
         * 然后就和刚才的算法一样了，交换基数和中间值，并返回中间值的下标。
         * 同样地，需要在退出循环后，单独比较 left 和 right 的值。
         */
        // 取第一个数为基数
        int pivot = arr[start];
        // 从第二个数开始分区
        int left = start + 1;
        // 右边界
        int right = end;
        while (left < right) {
            // 找到第一个大于基数的位置
            while (left < right && arr[left] <= pivot) {
                left++;
            }
            // 找到第一个小于基数的位置
            while (left < right && arr[right] >= pivot) {
                right--;
            }
            // 交换这两个数，使得左边分区都小于或等于基数，右边分区大于或等于基数
            if (left < right) {
                swap(arr, left, right);
                left++;
                right--;
            }
        }
        // 如果 left 和 right 相等，单独比较 arr[right] 和 pivot
        if (left == right && arr[right] > pivot) {
            right--;
        }
        // 将基数和轴交换
        swap(arr, start, right);
        return right;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        /*
         * 快速排序的优化思路：
         * 第一种就是每轮选择基数时，从剩余的数组中随机选择一个数字作为基数。
         * 这样每轮都选到最大或最小值的概率就会变得很低了。所以才说用这种方式选择基数，其平均时间复杂度是最优的。
         *
         * 第二种解决方案是在排序之前，先用洗牌算法将数组的原有顺序打乱，以防止原数组正序或逆序。
         * Java 已经将洗牌算法封装到了集合类中，即 Collections.shuffle() 函数。
         * 洗牌算法由 Ronald A.Fisher 和 Frank Yates 于 1938 年发明，思路是每次从未处理的数据中随机取出一个数字，然后把该数字放在数组中所有未处理数据的尾部。
         */
        int[] arr;
        arr = new int[]{1200, 292, 121, 72, 233, 44, 12};
        new QuickSort().quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
