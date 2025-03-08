package org.example.SortedAlgorithm;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

//使用随机数作为基准点，避免万一最大值或最小值作为基准点导致的分区不均衡
public class QuickSortRandomPivot {

    public static void sort(int[] arr) {
        quick(arr, 0, arr.length - 1);
    }

    private static void quick(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int p = partition(arr, left, right);
        quick(arr, left, p - 1);
        quick(arr, p + 1, right);
    }

    private static int partition(int[] arr, int left, int right) {
        /*
        new Random().nextInt(right - left + 1) 生成的随机数范围是 [0, right - left]，但这个范围是相对于子数组的起始位置 left 的偏移量。
        为了得到子数组中的实际索引，我们需要将这个偏移量加上 left。
        例如：
        假设 left = 2，right = 5，子数组是 [arr[2], arr[3], arr[4], arr[5]]。
        new Random().nextInt(right - left + 1) 会生成 0, 1, 2, 3 中的一个随机数。
        如果生成的随机数是 1，那么 left + 1 = 2 + 1 = 3，即随机选择的索引是 3，对应子数组中的 arr[3]。
        如果不加上 left，随机数的范围会是 [0, right - left]，这会导致选择的索引始终是相对于子数组的起始位置 0，而不是相对于整个数组的起始位置 left。
         */
        int index = ThreadLocalRandom.current().nextInt(right - left + 1);
        swap(arr, index, right);
        int pivot = arr[right];//基准点元素值
        int i = 0;
        int j = 0;
        while (j < right) {
            if (arr[j] < pivot) {//j找到比基准点小的时
                if (j != i) {//与i不相等
                    swap(arr, i, j);//与i元素交换
                }
                i++;//i指针向前+1
            }
            j++;
        }
        swap(arr, i, right);//最后基准点与 i 交换，i 即为基准点最终索引
        return i;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {4, 656, 12, 787, 124, 878, 47, 54, 65,};
        System.out.println(Arrays.toString(arr));
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
