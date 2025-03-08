package org.example.SortedAlgorithm;

import java.util.Arrays;

/**
 * 每一轮选择，找出最大（最小）的元素，并把它交换到合适的位置
 */
public class SelectionSort {
    public static void selectionSort(int[] arr) {
        // 1. 选择轮数 a.length - 1
        // 2. 交换的索引位置(right) 初始 a.length - 1, 每次递减
        for(int right=arr.length-1;right>0;right--){
            for(int i=right-1;i>=0;i--){
                if(arr[i]>arr[right]){
                    swap(arr,i,right);
                }
            }
        }
    }

    //递归
    public static void selectionSortRecursion(int[] arr,int right) {
        if(right==0){
            return;
        }
        for(int i=right-1;i>=0;i--){
            if(arr[i]>arr[right]){
                swap(arr,i,right);
            }
        }
        selectionSortRecursion(arr,right-1);
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {4, 656, 12, 787, 124, 878, 47, 54, 65,};
        System.out.println(Arrays.toString(arr));
//        selectionSort(arr);
        selectionSortRecursion(arr,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
}
