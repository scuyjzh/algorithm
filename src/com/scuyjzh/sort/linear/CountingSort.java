package com.scuyjzh.sort.linear;

import java.util.*;

/**
 * 计数排序（Counting sort）是一种稳定的线性时间排序算法。该算法于1954年由 Harold H. Seward 提出。
 * <p>
 * 从计数排序的实现代码中，可以看到，每次遍历都是进行 n 次或者 k 次，所以计数排序的时间复杂度为 O(n+k)，k 表示数据的范围大小。
 * 用到的空间主要是长度为 k 的计数数组和长度为 n 的结果数组，所以空间复杂度也是 O(n+k)。
 * <p>
 * 计数排序只适用于数据范围不大的场景。
 * 例如对考试成绩排序就非常适合计数排序，如果需要排序的数字中存在一位小数，可以将所有数字乘以 10，再去计算最终的下标位置。
 */
class CountingSort {
    /**
     * 伪计数排序
     */
    public void countingSort9(int[] arr) {
        /*
         * 举个例子，需要对一列数组排序，这个数组中每个元素都是 [1, 9] 区间内的整数。
         * 那么可以构建一个长度为 9 的数组用于计数，计数数组的下标分别对应区间内的 9 个整数。
         * 然后遍历待排序的数组，将区间内每个整数出现的次数统计到计数数组中对应下标的位置。
         * 最后遍历计数数组，将每个元素输出，输出的次数就是对应位置记录的次数。
         *
         * 算法非常简单，但这里的排序算法 并不是 真正的计数排序。
         * 因为现在的实现有一个非常大的弊端：排序完成后，arr 中记录的元素已经不再是最开始的那个元素了，他们只是值相等，但却不是同一个对象。
         * 在纯数字排序中，这个弊端或许看起来无伤大雅，但在实际工作中，这样的排序算法几乎无法使用。
         * 因为被排序的对象往往都会携带其他的属性，但这份算法将被排序对象的其他属性都丢失了。
         */
        // 建立长度为 9 的数组，下标 0~8 对应数字 1~9
        int[] counting = new int[9];
        // 遍历 arr 中的每个元素
        for (int element : arr) {
            // 将每个整数出现的次数统计到计数数组中对应下标的位置
            counting[element - 1]++;
        }
        int index = 0;
        // 遍历计数数组，将每个元素输出
        for (int i = 0; i < 9; ++i) {
            // 输出的次数就是对应位置记录的次数
            while (counting[i] != 0) {
                arr[index++] = i + 1;
                counting[i]--;
            }
        }
    }

    /**
     * 伪计数排序 2.0
     */
    public void countingSort99(int[] arr) {
        /*
         * 对于这个问题，很容易想到一种解决方案：
         * 在统计元素出现的次数时，同时把真实的元素保存到列表中，输出时，从列表中取真实的元素。
         *
         * 在这份代码中，通过队列来保存真实的元素，计数完成后，将队列中真实的元素赋到 arr 列表中，
         * 这就解决了信息丢失的问题，并且使用队列还可以保证排序算法的稳定性。
         * 但是，这也不是真正的计数排序，计数排序中使用了一种更巧妙的方法解决这个问题。
         */
        // 建立长度为 9 的数组，下标 0~8 对应数字 1~9
        int[] counting = new int[9];
        // 记录每个下标中包含的真实元素，使用队列可以保证排序的稳定性
        HashMap<Integer, Queue<Integer>> records = new HashMap<>();
        // 遍历 arr 中的每个元素
        for (int element : arr) {
            // 将每个整数出现的次数统计到计数数组中对应下标的位置
            counting[element - 1]++;
            if (!records.containsKey(element - 1)) {
                records.put(element - 1, new LinkedList<>());
            }
            records.get(element - 1).add(element);
        }
        int index = 0;
        // 遍历计数数组，将每个元素输出
        for (int i = 0; i < 9; ++i) {
            // 输出的次数就是对应位置记录的次数
            while (counting[i] != 0) {
                // 输出记录的真实元素
                arr[index++] = records.get(i).remove();
                counting[i]--;
            }
        }
    }

    /**
     * 真正的计数排序
     */
    public void countingSort1(int[] arr) {
        /*
         * 区别就在于计数排序并不是把计数数组的下标直接作为结果输出，而是通过计数的结果，
         * 计算出每个元素在排序完成后的位置，然后将元素赋值到对应位置。
         *
         * 首先将每位元素出现的次数记录到 counting 数组中。
         * 然后将 counting[i] 更新为数字 i 在最终排序结果中的起始下标位置。这个位置等于前面比自己小的数字的总数。
         * 接下来从头访问 arr 数组，根据 counting 中计算出的下标位置，将 arr 的每个元素直接放到最终位置上，然后更新 counting 中的下标位置。
         * 最后将 result 数组赋值回 arr，完成排序。
         */
        if (arr == null || arr.length <= 1) {
            return;
        }
        // 找到最大值，最小值
        int max = arr[0];
        int min = arr[0];
        for (int i = 1; i < arr.length; ++i) {
            if (arr[i] > max) {
                max = arr[i];
            } else if (arr[i] < min) {
                min = arr[i];
            }
        }
        // 确定计数范围
        int range = max - min + 1;
        // 建立长度为 range 的数组，下标 0~range-1 对应数字 min~max
        int[] counting = new int[range];
        // 遍历 arr 中的每个元素
        for (int element : arr) {
            // 将每个整数出现的次数统计到计数数组中对应下标的位置，这里需要将每个元素减去 min，才能映射到 0～range-1 范围内
            counting[element - min]++;
        }
        // 记录前面比自己小的数字的总数
        int preCounts = 0;
        for (int i = 0; i < range; i++) {
            // 当前的数字比下一个数字小，累计到 preCounts 中
            preCounts += counting[i];
            // 将 counting 计算成当前数字在结果中的起始下标位置。位置 = 前面比自己小的数字的总数
            counting[i] = preCounts - counting[i];
        }
        int[] result = new int[arr.length];
        for (int element : arr) {
            // counting[element - min] 表示此元素在结果数组中的下标
            result[counting[element - min]] = element;
            // 更新 counting[element - min]，指向此元素的下一个下标
            counting[element - min]++;
        }
        // 将结果赋值回 arr
        System.arraycopy(result, 0, arr, 0, arr.length);
    }

    /**
     * 倒序遍历的计数排序
     */
    public void countingSort2(int[] arr) {
        /*
         * 计数排序还有一种写法，在计算元素在最终结果数组中的下标位置这一步，不是计算初始下标位置，而是计算最后一个下标位置。
         * 最后倒序遍历 arr 数组，逐个将 arr 中的元素放到最终位置上。
         *
         * 两种算法的核心思想是一致的，并且都是稳定的。
         * 第一种写法理解起来简单一些，第二种写法在性能上更好一些。
         * 在计算下标位置时，不仅计算量更少，还省去了 preCounts 这个变量。
         * 实际上，这个算法最后不通过倒序遍历也能得到正确的排序结果，但这里只有通过倒序遍历的方式，才能保证计数排序的稳定性。
         */
        if (arr == null || arr.length <= 1) {
            return;
        }
        // 找到最大值，最小值
        int max = arr[0];
        int min = arr[0];
        for (int i = 1; i < arr.length; ++i) {
            if (arr[i] > max) {
                max = arr[i];
            } else if (arr[i] < min) {
                min = arr[i];
            }
        }
        // 确定计数范围
        int range = max - min + 1;
        // 建立长度为 range 的数组，下标 0~range-1 对应数字 min~max
        int[] counting = new int[range];
        // 遍历 arr 中的每个元素
        for (int element : arr) {
            // 将每个整数出现的次数统计到计数数组中对应下标的位置，这里需要将每个元素减去 min，才能映射到 0～range-1 范围内
            counting[element - min]++;
        }
        // 每个元素在结果数组中的最后一个下标位置 = 前面比自己小的数字的总数 + 自己的数量 - 1。将 counting[0] 先减去 1，后续 counting 直接累加即可
        counting[0]--;
        for (int i = 1; i < range; ++i) {
            // 将 counting 计算成当前数字在结果中的最后一个下标位置。位置 = 前面比自己小的数字的总数 + 自己的数量 - 1
            // 由于 counting[0] 已经减了 1，所以后续的减 1 可以省略
            counting[i] += counting[i - 1];
        }
        int[] result = new int[arr.length];
        // 从后往前遍历数组，通过 counting 中记录的下标位置，将 arr 中的元素放到 result 数组中
        for (int i = arr.length - 1; i >= 0; --i) {
            // counting[arr[i] - min] 表示此元素在结果数组中的下标
            result[counting[arr[i] - min]] = arr[i];
            // 更新 counting[arr[i] - min]，指向此元素的前一个下标
            counting[arr[i] - min]--;
        }
        // 将结果赋值回 arr
        System.arraycopy(result, 0, arr, 0, arr.length);
    }

    public static void main(String[] args) {
        int[] arr;
        arr = new int[]{5, 8, 1, 2, 7, 4, 8, 2, 2, 4};
        new CountingSort().countingSort9(arr);
        System.out.println(Arrays.toString(arr));

        arr = new int[]{5, 8, 1, 2, 7, 4, 8, 2, 2, 4};
        new CountingSort().countingSort99(arr);
        System.out.println(Arrays.toString(arr));

        arr = new int[]{5, 8, 1, 2, 7, 4, 8, 2, 2, 4};
        new CountingSort().countingSort1(arr);
        System.out.println(Arrays.toString(arr));

        arr = new int[]{5, 8, 1, 2, 7, 4, 8, 2, 2, 4};
        new CountingSort().countingSort2(arr);
        System.out.println(Arrays.toString(arr));
    }
}
