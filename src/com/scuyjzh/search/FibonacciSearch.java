package com.scuyjzh.search;

/**
 * Fibonacci Search is a comparison-based technique that uses Fibonacci numbers to search an element in a sorted array.
 *
 * @author scuyjzh
 * @date 2020/8/11 16:27
 */
class FibonacciSearch {
    public int search(int[] arr, int key) {
        int low, high;
        // 创建最大值刚好大于等于待查找数组长度的裴波纳契数组
        int[] fiboArray = makeFiboArray(arr.length);
        // 获取创建好的裴波那契数组最大值
        int filledLength = fiboArray[fiboArray.length - 1];
        // 创建长度等于裴波那契数组最大值的填充数组
        int[] filledArray = new int[filledLength];
        // 将待排序数组的元素依次放入填充数组中
        for (int i = 0; i < arr.length; i++) {
            filledArray[i] = arr[i];
        }
        // 如果填充数组还有空的元素，用原数组最后一个元素值填满
        int last = arr[arr.length - 1];
        for (int i = arr.length; i < filledLength; i++) {
            filledArray[i] = last;
        }

        // 取得待排序数组的长度 （注意是原数组！）
        low = 0;
        high = arr.length;
        int mid;
        int k = fiboArray.length - 1;
        while (low <= high) {
            mid = low + fiboArray[k - 1] - 1;
            // 排除右半边的元素
            if (key < filledArray[mid]) {
                high = mid - 1;
                // f(k-1)是左半边的长度
                k = k - 1;
            }
            // 排除左半边的元素
            else if (key > filledArray[mid]) {
                low = mid + 1;
                // f(k-2)是右半边的长度
                k = k - 2;
            }
            // 查找到目标值
            else {
                // 说明取到了填充数组末尾的重复元素了
                if (mid > high) {
                    return high;
                }
                // 说明没有取到填充数组末尾的重复元素
                else {
                    return mid;
                }
            }
        }
        return -1;
    }

    private int[] makeFiboArray(int N) {
        int first = 1, sec = 1, third = 2, fbLength = 2;
        // 使得裴波那契数不断递增，直到值刚好大于等于原数组长度为止
        while (third < N) {
            // 根据f(n) = f(n-1)+ f(n-2)计算
            third = first + sec;
            first = sec;
            sec = third;
            // 计算最后得到的裴波那契数组的长度
            fbLength++;
        }

        // 根据上面计算的长度创建一个空数组
        int[] fb = new int[fbLength];
        // 第一和第二个数是迭代计算裴波那契数的基础
        fb[0] = 1;
        fb[1] = 1;
        for (int i = 2; i < fbLength; i++) {
            // 将计算出的裴波那契数依次放入上面的空数组中
            fb[i] = fb[i - 1] + fb[i - 2];
        }
        return fb;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        FibonacciSearch fibonacciSearch = new FibonacciSearch();
        int res = fibonacciSearch.search(arr, 7);
        System.out.println(res);
    }
}
