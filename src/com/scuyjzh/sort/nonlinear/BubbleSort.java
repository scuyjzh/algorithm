package com.scuyjzh.sort.nonlinear;

import java.util.*;

/**
 * 冒泡排序（Bubble Sort）又称为泡式排序，是一种简单的排序算法。
 * 它重复地走访过要排序的数列，一次比较两个元素，如果他们的顺序错误就把他们交换过来。
 * 走访数列的工作是重复地进行直到没有再需要交换，也就是说该数列已经排序完成。
 * 这个算法的名字由来是因为越小的元素会经由交换慢慢“浮”到数列的顶端。
 * <p>
 * 冒泡排序是入门级的算法，但也有一些有趣的玩法。通常来说，冒泡排序有三种写法：
 * • 一边比较一边向后两两交换，将最大值 / 最小值冒泡到最后一位；
 * • 经过优化的写法：使用一个变量记录当前轮次的比较是否发生过交换，如果没有发生交换表示已经有序，不再继续排序；
 * • 进一步优化的写法：除了使用变量记录当前轮次是否发生交换外，再使用一个变量记录上次发生交换的位置，下一轮排序时到达上次交换的位置就停止比较。
 */
class BubbleSort {
    /**
     * 冒泡排序的第一种写法
     */
    public void bubbleSort1(int[] arr) {
        /*
         * 最外层的 for 循环每经过一轮，剩余数字中的最大值就会被移动到当前轮次的最后一位，中途也会有一些相邻的数字经过交换变得有序。
         * 总共比较次数是 (n−1)+(n−2)+(n−3)+…+1。
         * 这种写法相当于相邻的数字两两比较，并且规定：“谁大谁站右边”。
         * 经过 n-1n−1 轮，数字就从小到大排序完成了。
         * 整个过程看起来就像一个个气泡不断上浮，这也是“冒泡排序法”名字的由来。
         */
        for (int i = 0; i < arr.length - 1; ++i) {
            for (int j = 0; j < arr.length - 1 - i; ++j) {
                if (arr[j] > arr[j + 1]) {
                    // 如果左边的数大于右边的数，则交换，保证右边的数字最大
                    swap(arr, j, j + 1);
                }
            }

            // 调试语句
            System.out.println("round " + (i + 1) + ": " + Arrays.toString(arr));
        }
    }

    /**
     * 冒泡排序的第二种写法
     */
    public void bubbleSort2(int[] arr) {
        /*
         * 第二种写法是在第一种写法的基础上改良而来的：
         * 最外层的 for 循环每经过一轮，剩余数字中的最大值仍然是被移动到当前轮次的最后一位。
         * 这种写法相对于第一种写法的优点是：如果一轮比较中没有发生过交换，则立即停止排序，因为此时剩余数字一定已经有序了。
         */
        // 初始时 swapped 为 true，否则排序过程无法启动
        boolean swapped = true;
        for (int i = 0; i < arr.length - 1; ++i) {
            // 如果没有发生过交换，说明剩余部分已经有序，排序完成
            if (!swapped) {
                break;
            }
            // 设置 swapped 为 false，如果发生交换，则将其置为 true
            swapped = false;
            for (int j = 0; j < arr.length - 1 - i; ++j) {
                if (arr[j] > arr[j + 1]) {
                    // 如果左边的数大于右边的数，则交换，保证右边的数字最大
                    swap(arr, j, j + 1);
                    // 表示发生了交换
                    swapped = true;
                }
            }

            // 调试语句
            System.out.println("round " + (i + 1) + ": " + Arrays.toString(arr));
        }
    }

    /**
     * 冒泡排序的第三种写法
     */
    public void bubbleSort3(int[] arr) {
        /*
         * 第三种写法比较少见，它是在第二种写法的基础上进一步优化：
         * 最外层的 while 循环每经过一轮，剩余数字中的最大值仍然是被移动到当前轮次的最后一位。
         * 在下一轮比较时，只需比较到上一轮比较中，最后一次发生交换的位置即可。因为后面的所有元素都没有发生过交换，必然已经有序了。
         * 当一轮比较中从头到尾都没有发生过交换，则表示整个列表已经有序，排序完成。
         */
        boolean swapped = true;
        // 最后一个没有经过排序的元素的下标
        int indexOfLastUnsortedElement = arr.length - 1;
        // 上次发生交换的位置
        int swappedIndex = -1;
        while (swapped) {
            swapped = false;
            for (int i = 0; i < indexOfLastUnsortedElement; i++) {
                if (arr[i] > arr[i + 1]) {
                    // 如果左边的数大于右边的数，则交换，保证右边的数字最大
                    swap(arr, i, i + 1);
                    // 表示发生了交换
                    swapped = true;
                    // 更新交换的位置
                    swappedIndex = i;
                }
            }
            // 最后一个没有经过排序的元素的下标就是最后一次发生交换的位置
            indexOfLastUnsortedElement = swappedIndex;
        }
    }

    /**
     * 交换元素
     */
    private void swap(int[] arr, int i, int j) {
        // arr[i] = arr[i] + arr[j];
        // arr[j] = arr[i] - arr[j];
        // arr[i] = arr[i] - arr[j];
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr;
        arr = new int[]{8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        new BubbleSort().bubbleSort1(arr);
        System.out.println("sorted: " + Arrays.toString(arr));

        arr = new int[]{8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        new BubbleSort().bubbleSort2(arr);
        System.out.println("sorted: " + Arrays.toString(arr));

        arr = new int[]{8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        new BubbleSort().bubbleSort3(arr);
        System.out.println("sorted: " + Arrays.toString(arr));
    }
}
