package com.scuyjzh.search;

/**
 * @author scuyjzh
 * @date 2020/6/29 11:25
 */
class BinarySearch {
    public int search(int[] arr, int target) {
        int low = 0, high = arr.length - 1, mid;
        if (target < arr[low] || target > arr[high] || low > high) {
            return -1;
        }

        while (low <= high) {
            // mid = (high + low) / 2; // 大数相加会溢出
            mid = low + (high - low) / 2;
            if (arr[mid] > target) {
                // 比关键字大则关键字在左区域
                high = mid - 1;
            } else if (arr[mid] < target) {
                // 比关键字小则关键字在右区域
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 5, 7, 21, 23, 35};
        BinarySearch binarySearch = new BinarySearch();
        int res = binarySearch.search(arr, 21);
        System.out.println(res);
    }
}
