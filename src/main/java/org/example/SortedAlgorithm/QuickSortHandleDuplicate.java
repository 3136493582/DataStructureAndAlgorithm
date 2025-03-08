package org.example.SortedAlgorithm;

import java.util.Arrays;
import java.util.Random;

/*
处理带有大量重复元素的数组
 */
public class QuickSortHandleDuplicate {
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

    /*
        循环内
            i 从 left + 1 开始，从左向右找大的或相等的
            j 从 right 开始，从右向左找小的或相等的
            交换，i++ j--

        循环外 j 和 基准点交换，j 即为分区位置
     */
    private static int partition(int[] arr, int left, int right) {
        int index=new Random().nextInt(right-left+1) + left;
        swap(arr, index, left);
        int pivot=arr[left];
        int i=left+1;
        int j=right;
        while (i<=j){
            //从左向右找大的或者相等的
            while (i<=j && arr[i]<pivot){
            i++;
            }
            //从右向左找小的或者相等的
            while (i<=j && arr[j]>pivot){
                j--;
            }

            if(i<=j){
                swap(arr, i, j);
                i++;
                j--;
            }
        }
        swap(arr, j, left);
        return j;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {4, 656, 6, 787, 124, 4, 4, 54, 6,};
        System.out.println(Arrays.toString(arr));
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
