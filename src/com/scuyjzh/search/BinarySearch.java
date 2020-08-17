package com.scuyjzh.search;

/**
 * 二分查找算法是一种在有序数组中查找某一特定元素的搜索算法。
 * 搜索过程从数组的中间元素开始，如果中间元素正好是要查找的元素，则搜索过程结束；
 * 如果某一特定元素大于或者小于中间元素，则在数组大于或小于中间元素的那一半中查找，而且跟开始一样从中间元素开始比较。如果在某一步骤数组为空，则代表找不到。
 * 这种搜索算法每一次比较都使搜索范围缩小一半。
 *
 * @author scuyjzh
 * @date 2020/6/29 11:25
 */
class BinarySearch {
    public int search(int[] arr, int key) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            // Prevent '+' integer overflow
            int mid = low + (high - low) / 2;
            // check if x is present at mid
            if (arr[mid] == key) {
                return mid;
            }
            // if key is larger, ignore left half
            if (arr[mid] < key) {
                low = mid + 1;
            }
            // if key is smaller, ignore right half
            else {
                high = mid - 1;
            }
        }
        // if we reach here, then element was not present
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 5, 7, 21, 23, 35};
        BinarySearch binarySearch = new BinarySearch();
        int res = binarySearch.search(arr, 21);
        System.out.println(res);
    }
}
