package com.scuyjzh.search;

/**
 * 插值搜索法是利用插值公式来计算猜测搜索键值的位置。搜索方式与二分搜索相同。
 * 插值公式：
 * 插值 = (设算数 - 最小数) / (最大数 - 最小数)：
 * 搜索键值 = left + parseInt( ( key - data[left] ) / ( data[right] - data[left] ) )*( right - left ) )
 *
 * @author scuyjzh
 * @date 2020/8/11 16:03
 */
class InterpolationSearch {
    public int search(int[] arr, int key) {
        int low = 0, high = arr.length - 1;
        // Since array is sorted, an element present
        // in array must be in range defined by corner
        while (low <= high && key >= arr[low] && key <= arr[high]) {
            if (low == high) {
                if (arr[low] == key) {
                    return low;
                }
                return -1;
            }
            // Probing the position with keeping
            // uniform distribution in mind
            int pos = low + (key - arr[low]) * (high - low) / (arr[high] - arr[low]);
            // Condition of target found
            if (arr[pos] == key) {
                return pos;
            }
            // If key is larger, key is in upper part
            if (arr[pos] < key) {
                low = pos + 1;
            }
            // If key is smaller, key is in the lower part
            else {
                high = pos - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 4, 6, 9, 11, 66, 78};
        InterpolationSearch interpolationSearch = new InterpolationSearch();
        int res = interpolationSearch.search(arr, 11);
        System.out.println(res);
    }
}
