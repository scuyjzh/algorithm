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
        while (low <= high) {
            int mid = low + (high - low) * (key - arr[low]) / (arr[high] - arr[low]);
            int midVal = arr[mid];
            if (midVal < key) {
                low = mid + 1;
            } else if (midVal > key) {
                high = mid - 1;
            } else {
                // key found
                return mid;
            }
        }
        // key not found
        return -(low + 1);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 5, 7, 21, 23, 35};
        InterpolationSearch interpolationSearch = new InterpolationSearch();
        int res = interpolationSearch.search(arr, 21);
        System.out.println(res);
    }
}
